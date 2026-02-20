package com.corpcazorla.proyecto.application.service;

import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


@ApplicationScoped
public class ListarExamenesPacienteService {
    @Inject
    PacienteRepositoryPort repository;

    public List<Paciente> ejecutar(PacienteRequest req) {
        return repository.buscarConFiltros(
            req.getSexo(),
            req.getNombrePaciente(),
            req.getFechaInicio(),
            req.getFechaFin(),
            req.getPagina(),
            req.getSize()
        );
    }

}
