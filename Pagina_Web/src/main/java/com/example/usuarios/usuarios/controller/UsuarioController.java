package com.example.usuarios.usuarios.controller;

import com.example.usuarios.usuarios.model.Usuario;
import com.example.usuarios.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@Controller
@RequestMapping("/usuarios")
/**
 * Clase UsuarioController.
 */
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
/**
 * Método listarUsuarios.
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // Vista que mostrarás (usuarios.html)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
/**
 * Método eliminarUsuario.
 * @param id parámetro de entrada
 * @return valor de retorno
 */
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detalles/{id}")
/**
 * Método verDetallesUsuario.
 * @param id parámetro de entrada
 * @param model parámetro de entrada
 * @return valor de retorno
 */
    public String verDetallesUsuario(@PathVariable Long id, Model model) {
        usuarioRepository.findById(id).ifPresent(usuario -> model.addAttribute("usuario", usuario));
        return "usuario_detalles";
    }
}
