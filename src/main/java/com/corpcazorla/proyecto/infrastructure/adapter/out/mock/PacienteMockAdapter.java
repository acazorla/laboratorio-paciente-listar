package com.corpcazorla.proyecto.infrastructure.adapter.out.mock;

import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;

//import io.quarkus.arc.lookup.LookupIfProperty;
//import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

import java.util.List;


//@Priority(1)
//@LookupIfProperty(name = "adapter.mode", stringValue = "mock")
@ApplicationScoped
@Alternative
public class PacienteMockAdapter implements PacienteRepositoryPort {

    @Override
    public List<Paciente> buscarConFiltros(
            String sexo,
            String nombrePaciente,
            String fechaInicio,
            String fechaFin,
            int pagina,
            int size) {

      return PacienteMapper.mockList();
    }
}