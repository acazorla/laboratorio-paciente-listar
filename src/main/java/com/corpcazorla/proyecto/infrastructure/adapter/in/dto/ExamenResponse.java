package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;

public class ExamenResponse {

    private String nombre;
    private String fecha;
    private String archivo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
    
}
