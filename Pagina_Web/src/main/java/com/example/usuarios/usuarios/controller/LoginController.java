package com.example.usuarios.usuarios.controller;

import com.example.usuarios.usuarios.model.Usuario;
import com.example.usuarios.usuarios.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/**
 * Clase LoginController.
 */
public class LoginController {
    int contador = 0;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
/**
 * Método mostrarLoginForm.
 * @return valor de retorno
 */
    public String mostrarLoginForm() {
        return "login";
    }



    @GetMapping("/logout")
/**
 * Método logout.
 * @param session parámetro de entrada
 * @return valor de retorno
 */
    public String logout(HttpSession session) {
        session.invalidate(); // Opcional si quieres hacerlo manualmente
        return "redirect:/login?logout";
    }

}

