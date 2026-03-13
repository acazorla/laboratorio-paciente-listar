package com.corpcazorla.proyecto.application.service;

import com.corpcazorla.proyecto.domain.model.DataPage;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;

public interface ListarExamenesPacienteServiceImpl {
	DataPage<Paciente> listarPaciente(PacienteRequest request);

}
