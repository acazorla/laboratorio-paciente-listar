package com.corpcazorla.complaints.application.service;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;

public interface RegisterComplaintServiceImpl {
	DataPage<Complaint> listarPaciente(ComplaintsRequest request);

}
