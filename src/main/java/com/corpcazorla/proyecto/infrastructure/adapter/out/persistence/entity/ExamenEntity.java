package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDate;

public class ExamenEntity {
    private String nombre;
    private LocalDate fecha;
    private String rutaArchivo;
    
	public String getNombre() {
		return nombre;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public String getRutaArchivo() {
		return rutaArchivo;
	}
    
    
}
