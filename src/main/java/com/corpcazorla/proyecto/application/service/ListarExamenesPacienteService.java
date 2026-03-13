package com.corpcazorla.proyecto.application.service;

import com.corpcazorla.proyecto.domain.model.DataPage;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ListarExamenesPacienteService {
	private final PacienteRepositoryPort pacienteRepositoryPort;

    public ListarExamenesPacienteService(PacienteRepositoryPort repository) {
        this.pacienteRepositoryPort = repository;
    }
    public DataPage<Paciente> listarPaciente(PacienteRequest request) {
        return pacienteRepositoryPort.buscarPacienteConFiltros(request);
    }

}
