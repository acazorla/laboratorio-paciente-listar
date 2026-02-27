package com.corpcazorla.proyecto.application.service;

import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ListarExamenesPacienteService {
	private final PacienteRepositoryPort pacienteRepositoryPort;

    public ListarExamenesPacienteService(PacienteRepositoryPort repository) {
        this.pacienteRepositoryPort = repository;
    }
    public List<Paciente> ejecutar(PacienteRequest req) {
        return pacienteRepositoryPort.buscarConFiltros(
            req.getSexo(),
            req.getNombrePaciente(),
            req.getFechaInicio(),
            req.getFechaFin(),
            req.getPagina(),
            req.getSize()
        );
    }

}
