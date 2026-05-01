package com.corpcazorla.complaints.domain.ports.out;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;

public interface ComplaintRepositoryPort {
	DataPage<Complaint> register(ComplaintsRequest request);
}
