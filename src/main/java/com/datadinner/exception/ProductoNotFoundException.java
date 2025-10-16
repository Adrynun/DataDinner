package com.datadinner.exception;

public class ProductoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductoNotFoundException(int id) {
		super("Producto con id " + id + " no encontrado");
	}
}
