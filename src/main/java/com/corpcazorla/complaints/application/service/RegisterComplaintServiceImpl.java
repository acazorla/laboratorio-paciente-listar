package com.corpcazorla.complaints.application.service;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.domain.ports.out.ComplaintRepositoryPort;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegisterComplaintServiceImpl implements RegisterComplaintService{
	private final ComplaintRepositoryPort complaintRepositoryPort;

    public RegisterComplaintServiceImpl(ComplaintRepositoryPort repository) {
        this.complaintRepositoryPort = repository;
    }
    @Override
    public DataPage<Complaint> registerComplaint(ComplaintsRequest request) {
        return complaintRepositoryPort.register(request);
    }

}
