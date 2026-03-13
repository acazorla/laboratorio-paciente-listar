package com.corpcazorla.proyecto.domain.ports.out;

import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;
import com.corpcazorla.proyecto.domain.model.DataPage;
import com.corpcazorla.proyecto.domain.model.Paciente;

public interface PacienteRepositoryPort {
	DataPage<Paciente> buscarPacienteConFiltros(PacienteRequest request);
}
