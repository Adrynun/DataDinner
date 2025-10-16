package com.datadinner.exception;

public class MesaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MesaNotFoundException(int id) {
        super("Mesa no encontrada con id " + id);
    }
}
