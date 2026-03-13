package com.corpcazorla.proyecto.domain.model;

import java.util.List;

/**
 * Contenedor genérico para resultados paginados en la capa de dominio.
 * @param <T> El tipo de objeto de dominio (ej. Paciente, Examen).
 */
public class DataPage<T> {
	
	private final List<T> data;
    private final long totalElements;

    public DataPage(List<T> data, long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
