package com.example.usuarios.usuarios.repository;

import com.example.usuarios.usuarios.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Clase ProductoRepository.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
