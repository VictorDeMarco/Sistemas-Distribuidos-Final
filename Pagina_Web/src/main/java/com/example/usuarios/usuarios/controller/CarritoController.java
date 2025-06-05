package com.example.usuarios.usuarios.controller;

import com.example.usuarios.usuarios.model.ItemCarrito;
import com.example.usuarios.usuarios.model.Producto;
import com.example.usuarios.usuarios.repository.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;


import java.util.*;

@Controller
@RequestMapping("/carrito")
/**
 * Clase CarritoController.
 */
public class CarritoController {

    private final ProductoRepository productoRepository;

/**
 * Constructor de la clase CarritoController.
 */
    public CarritoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Long productoId,
                                   @RequestParam int cantidad,
                                   HttpSession session) {

        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }



        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto != null) {
            Optional<ItemCarrito> existente = carrito.stream()
                    .filter(item -> item.getProducto().getId().equals(productoId))
                    .findFirst();

            if (existente.isPresent()) {
                existente.get().setCantidad(existente.get().getCantidad() + cantidad);
            } else {
                carrito.add(new ItemCarrito(producto, cantidad));
            }
        }

        return "redirect:/productos";
    }

    @GetMapping
/**
 * Método verCarrito.
 * @param session parámetro de entrada
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String verCarrito(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();

        model.addAttribute("carrito", carrito);

        BigDecimal total = carrito.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("total", total);

        return "carrito";
    }


    @GetMapping("/vaciar")
/**
 * Método vaciarCarrito.
 * @param session parámetro de entrada
 * @return valor de retorno
 */
    public String vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/carrito";
    }
    @GetMapping("/finalizar")
/**
 * Método mostrarPasarelaPago.
 * @param session parámetro de entrada
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String mostrarPasarelaPago(HttpSession session, Model model) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/carrito"; // No se puede finalizar si está vacío
        }

        BigDecimal total = carrito.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("total", total);
        return "pago";
    }

    @PostMapping("/confirmar")
/**
 * Método confirmarCompra.
 * @param session parámetro de entrada
 * @return valor de retorno
 */
    public String confirmarCompra(HttpSession session) {
        session.removeAttribute("carrito"); // Vaciar carrito tras pago
        return "compra_exitosa";
    }
}
