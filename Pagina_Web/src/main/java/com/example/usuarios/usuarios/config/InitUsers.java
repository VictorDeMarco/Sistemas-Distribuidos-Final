package com.example.usuarios.usuarios.config;

import com.example.usuarios.usuarios.model.Usuario;
import com.example.usuarios.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
/**
 * Clase InitUsers.
 */
public class InitUsers implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

/**
 * Constructor de la clase InitUsers.
 */
    public InitUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
/**
 * Método run.
 * @param args parámetro de entrada
 */
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRol("ADMIN");
            usuarioRepository.save(admin);
            System.out.println("Usuario 'admin' creado con contraseña 'admin'");
        } else {
            System.out.println("Usuario 'admin' ya existe, no se creó");
        }
    }
}
