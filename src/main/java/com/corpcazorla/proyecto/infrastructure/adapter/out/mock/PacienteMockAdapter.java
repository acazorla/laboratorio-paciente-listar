package com.corpcazorla.proyecto.infrastructure.adapter.out.mock;

import com.corpcazorla.proyecto.domain.model.DataPage;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class PacienteMockAdapter implements PacienteRepositoryPort {

    @Override
    public DataPage<Paciente> buscarPacienteConFiltros(PacienteRequest request) {

      Long totalRegistros=(long) 50;
      return new DataPage<>(PacienteMapper.mockList(), totalRegistros);
    }
}