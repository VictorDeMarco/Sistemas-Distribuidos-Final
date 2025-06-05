package com.example.usuarios.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
/**
 * Clase ItemCarrito.
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

/**
 * Método getSubtotal.
 * @return valor de retorno
 */
    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }
}
