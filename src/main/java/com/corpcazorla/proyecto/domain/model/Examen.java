package com.corpcazorla.proyecto.domain.model;


public class Examen {
	private String nombre;
    private String fecha;
    private String archivo;

    public Examen(String nombre, String fecha, String archivo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.archivo = archivo;
    }

    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getArchivo() { return archivo; }

	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setFecha(String fecha) {this.fecha = fecha;}
	public void setArchivo(String archivo) {this.archivo = archivo;}
    
    
}
