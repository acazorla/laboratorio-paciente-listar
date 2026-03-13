package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class PacienteRequest {

	private String nombrePaciente;
	@Schema(description = "Sexo del paciente: M o F", enumeration = {"M", "F"})
	private String sexo;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de fechaInicio debe ser YYYY-MM-DD")
	private String fechaInicio;
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de fechaFin debe ser YYYY-MM-DD")
	private String fechaFin;
	@Min(value = 1, message = "La página debe ser mayor a 0")
	private int pagina = 1;
	@Min(value = 1)
    @Max(value = 100, message = "El tamaño máximo por página es 100")
	private int size = 10;

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
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
