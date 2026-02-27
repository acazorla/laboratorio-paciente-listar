package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Pagination pagination;
    private Meta meta;

 // 1. Constructor vacío: Esencial para Jackson y para el GlobalExceptionMapper
    public ApiResponse() {
        this.meta = new Meta(); // Evita que meta sea null en el JSON
    }
 // 2. Constructor para Éxito: El que usa tu PacienteController
    public ApiResponse(T data, Pagination pagination) {
        this(); // Llama al constructor vacío para inicializar el meta
        this.success = true;
        this.message = "Operación exitosa";
        this.data = data;
        this.pagination = pagination;
    }
	/*
	 * public ApiResponse(T data, Pagination pagination) { this.success = true;
	 * this.data = data; this.pagination = pagination; this.meta = new Meta(); }
	 */

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
