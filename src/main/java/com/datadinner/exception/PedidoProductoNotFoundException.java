package com.datadinner.exception;

public class PedidoProductoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoProductoNotFoundException(int id) {
		super("PedidoProducto no encontrado con id " + id);
	}
}
