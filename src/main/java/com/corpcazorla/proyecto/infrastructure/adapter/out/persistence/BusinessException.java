package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence;

public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int code;

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }
    public int getCode() { return code; }
}
