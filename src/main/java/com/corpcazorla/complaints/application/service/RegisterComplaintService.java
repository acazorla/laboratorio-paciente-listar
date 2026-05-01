package com.corpcazorla.complaints.application.service;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;

public interface RegisterComplaintService {
	DataPage<Complaint> registerComplaint(ComplaintsRequest request);

}
