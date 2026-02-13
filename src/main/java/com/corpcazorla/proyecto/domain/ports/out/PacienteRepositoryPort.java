package com.corpcazorla.proyecto.domain.ports.out;

import com.corpcazorla.proyecto.domain.model.Paciente;
import java.util.List;


public interface PacienteRepositoryPort {
    List<Paciente> buscarConFiltros(
            String sexo,
            String nombrePaciente,
            String fechaInicio,
            String fechaFin,
            int pagina,
            int size
        );
}
