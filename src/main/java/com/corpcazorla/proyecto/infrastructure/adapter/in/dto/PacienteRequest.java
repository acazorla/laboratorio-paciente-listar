package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;

import java.time.LocalDate;
public class PacienteRequest {
    public String sexo;
    public String tipoExamen;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;
    public int pagina = 0;
    public int size = 10;
}
