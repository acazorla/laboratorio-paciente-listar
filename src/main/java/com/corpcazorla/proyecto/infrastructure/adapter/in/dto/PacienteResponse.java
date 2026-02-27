package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;
import java.util.List;
public class PacienteResponse {
    private String id;
    private String nombrePaciente;
    private String sexo;
    private String fechaAtencion;
    private String fechaNacimiento;
    private List<ExamenResponse> examenes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<ExamenResponse> getExamenes() {
		return examenes;
	}
	public void setExamenes(List<ExamenResponse> examenes) {
		this.examenes = examenes;
	}
    
}
