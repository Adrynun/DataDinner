package com.datadinner.exception;

public class UsuarioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(int id) {
        super("Usuario no encontrado con id " + id);
    }
}
