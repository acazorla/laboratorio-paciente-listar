package com.corpcazorla.complaints.infrastructure.adapter.out.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;

import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ComplaintMapper {

	// MÉTODO: Convierte Dominio -> DTO de Respuesta
	public List<ComplaintResponse> toResponseList(List<Complaint> complaints) {
		return complaints.stream().map(this::toResponse).toList();
	}

	public ComplaintResponse toResponse(Complaint complaint) {
		ComplaintResponse dto = new ComplaintResponse();
		dto.setComplaintId(complaint.getComplaintId());
		dto.setTrackingCode(complaint.getTrackingCode());
		return dto;
	}

	public List<Complaint> fromRows(List<Object[]> rows) {
		if (rows == null || rows.isEmpty()) {
			return Collections.emptyList();
		}
		// Usamos LinkedHashMap para mantener el orden
		final Map<String, Complaint> complaintMap = new LinkedHashMap<>();

		for (Object[] row : rows) {
			// 1. Validación de fila nula o vacía
			if (row == null || row.length == 0 || row[0] == null)
				continue;

			final String complaintId = row[0].toString();

			// 2. Agrupación segura del Paciente
			complaintMap.computeIfAbsent(complaintId, id -> {
				final Complaint c = new Complaint();
				c.setComplaintId(id);
				c.setTrackingCode(safeString(row, 1, "PENDING"));
				return c;
			});

		}
		final List<Complaint> result = new ArrayList<>(complaintMap.size());
		result.addAll(complaintMap.values());
		return result;

	}

	// Método auxiliar utilitario para obtener datos de la fila de forma segura
	private String safeString(Object[] row, int index, String defaultValue) {
		return (row.length > index && row[index] != null) ? row[index].toString() : defaultValue;
	}

}
