package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity;

import java.util.List;

public class PacienteEntity {
    private String id;
    private String nombre;
    private String sexo;
    private List<ExamenEntity> examenes;
    
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public List<ExamenEntity> getExamenes() {
		return examenes;
	}
    
    
}
