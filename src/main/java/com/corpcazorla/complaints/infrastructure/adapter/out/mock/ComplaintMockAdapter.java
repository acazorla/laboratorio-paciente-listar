package com.corpcazorla.complaints.infrastructure.adapter.out.mock;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.domain.ports.out.ComplaintRepositoryPort;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;
import com.corpcazorla.complaints.infrastructure.adapter.out.persistence.mapper.ComplaintMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class ComplaintMockAdapter implements ComplaintRepositoryPort {

    @Override
    public DataPage<Complaint> register(ComplaintsRequest request) {

      Long totalRegistros=(long) 1;
      return new DataPage<>(ComplaintMapper.mockList(), totalRegistros);
    }
}