package com.example.usuarios.usuarios.controller;

import com.example.usuarios.usuarios.model.Producto;
import com.example.usuarios.usuarios.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/productos")
/**
 * Clase ProductoController.
 */
public class ProductoController {

    private final ProductoService productoService;

/**
 * Constructor de la clase ProductoController.
 */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
/**
 * Método listarProductos.
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
/**
 * Método mostrarFormularioNuevo.
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
/**
 * Método guardarProducto.
 * @param producto parámetro de entrada
 * @return valor de retorno
 */
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
/**
 * Método mostrarFormularioEditar.
 * @param id parámetro de entrada
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        productoService.buscarPorId(id).ifPresent(p -> model.addAttribute("producto", p));
        return "producto_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
/**
 * Método eliminarProducto.
 * @param id parámetro de entrada
 * @return valor de retorno
 */
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
