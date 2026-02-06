package com.corpcazorla.proyecto.domain.ports.out;

import com.corpcazorla.proyecto.domain.model.Paciente;
import java.time.LocalDate;
import java.util.List;


public interface PacienteRepositoryPort {
    List<Paciente> buscarConFiltros(
            String sexo,
            String tipoExamen,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            int pagina,
            int size
        );
}
