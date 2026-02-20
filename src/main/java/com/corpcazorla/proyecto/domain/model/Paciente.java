package com.corpcazorla.proyecto.domain.model;

import java.util.List;

public class Paciente {
    private String id;
    private String nombrePaciente;
    private String fechaAtencion;
    private String fechaNacimiento;
    private String sexo;
    private List<Examen> examenes;
    
    public Paciente() {
    }
    public Paciente(String id, String nombrePaciente,String sexo, List<Examen> examenes) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.sexo = sexo;
        this.examenes = examenes;
    }

    public String getId() { return id; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getFechaAtencion() { return fechaAtencion; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getSexo() { return sexo; }
    public List<Examen> getExamenes() { return examenes; }

    public void setId(String id) { this.id = id; } 
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; } 
	public void setFechaAtencion(String fechaAtencion) { this.fechaAtencion = fechaAtencion; }
	public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setExamenes(List<Examen> examenes) { this.examenes = examenes; }
	

	
    
}
