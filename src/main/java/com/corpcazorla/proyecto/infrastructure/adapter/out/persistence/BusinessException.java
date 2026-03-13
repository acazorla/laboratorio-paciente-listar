package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence;

public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final int code;

    // Constructor estándar
    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    // Constructor con Causa (Recomendado para persistencia)
    public BusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() { return code; }
}