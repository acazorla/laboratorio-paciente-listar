package com.corpcazorla.proyecto.infrastructure.adapter.out.mock;

import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;

@Alternative
@Priority(1)
@ApplicationScoped
public class PacienteMockAdapter implements PacienteRepositoryPort {

    @Override
    public List<Paciente> buscarConFiltros(
            String sexo,
            String tipoExamen,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            int pagina,
            int size) {

      return PacienteMapper.mockList();
    }
}