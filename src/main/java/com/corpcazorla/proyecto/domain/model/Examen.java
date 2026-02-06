package com.corpcazorla.proyecto.domain.model;

import java.time.LocalDate; 
public class Examen {
	private String nombre;
    private LocalDate fecha;
    private String archivo;

    public Examen(String nombre, LocalDate fecha, String archivo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.archivo = archivo;
    }

    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
    public String getArchivo() { return archivo; }

	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setFecha(LocalDate fecha) {this.fecha = fecha;}
	public void setArchivo(String archivo) {this.archivo = archivo;}
    
    
}
