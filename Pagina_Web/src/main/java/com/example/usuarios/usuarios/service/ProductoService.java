package com.example.usuarios.usuarios.service;

import com.example.usuarios.usuarios.model.Producto;
import com.example.usuarios.usuarios.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/**
 * Clase ProductoService.
 */
public class ProductoService {

    private final ProductoRepository productoRepository;

/**
 * Constructor de la clase ProductoService.
 */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

/**
 * Método listarTodos.
 * @return valor de retorno
 */
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

/**
 * Método buscarPorId.
 * @param id parámetro de entrada
 * @return valor de retorno
 */
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

/**
 * Método guardar.
 * @param producto parámetro de entrada
 */
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

/**
 * Método eliminar.
 * @param id parámetro de entrada
 */
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
