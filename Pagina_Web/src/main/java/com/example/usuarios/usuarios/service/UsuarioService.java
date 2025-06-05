package com.example.usuarios.usuarios.service;


import com.example.usuarios.usuarios.model.Usuario;
import com.example.usuarios.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
/**
 * Clase UsuarioService.
 */
public class UsuarioService {

    private final UsuarioRepository  usuarioRepository ;

/**
 * Constructor de la clase UsuarioService.
 */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}
