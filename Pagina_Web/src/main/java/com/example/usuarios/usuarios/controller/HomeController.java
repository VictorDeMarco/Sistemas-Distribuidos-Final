package com.example.usuarios.usuarios.controller;


import com.example.usuarios.usuarios.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
/**
 * Clase HomeController.
 */
public class HomeController {

    @GetMapping("/home")
/**
 * Método mostrarPaginaPrincipal.
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String mostrarPaginaPrincipal(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName(); // obtiene el nombre de usuario autenticado

        model.addAttribute("nombre", nombreUsuario);
        return "home";
    }
}
