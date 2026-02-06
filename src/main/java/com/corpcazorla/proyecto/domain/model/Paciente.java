package com.corpcazorla.proyecto.domain.model;

import java.util.List;

public class Paciente {
    private String id;
    private String nombre;
    private String sexo;
    private List<Examen> examenes;
    
    public Paciente() {
    }
    public Paciente(String id, String nombre,String sexo, List<Examen> examenes) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.examenes = examenes;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getSexo() { return sexo; }
    public List<Examen> getExamenes() { return examenes; }

    public void setId(String id) { this.id = id; } 
    public void setNombre(String nombre) { this.nombre = nombre; } 
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setExamenes(List<Examen> examenes) { this.examenes = examenes; }
	     
}
