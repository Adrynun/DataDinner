package com.datadinner.exception;

public class PedidoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PedidoNotFoundException(int id) {
        super("Pedido no encontrado con id " + id);
    }
}
