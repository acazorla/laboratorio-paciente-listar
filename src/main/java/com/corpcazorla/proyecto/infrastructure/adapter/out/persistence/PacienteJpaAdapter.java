package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence;

import com.corpcazorla.proyecto.domain.model.DataPage;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

@ApplicationScoped
@Default // Asegura que este sea el principal
@jakarta.annotation.Priority(10) // Un número alto le da más importancia que al Mock
public class PacienteJpaAdapter implements PacienteRepositoryPort {

    private final EntityManager entityManager;
    private final PacienteMapper mapper;

    public PacienteJpaAdapter(EntityManager entityManager, PacienteMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public DataPage<Paciente> buscarPacienteConFiltros(PacienteRequest request) {
    	try {
    		// 1. Definición de la consulta nativa
            Query query = entityManager.createNativeQuery("EXEC OCUPACIONAL.usp_ListarPacientesConExamenes :sexo, :tipo, :inicio, :fin, :pagina, :size");

            // 2. Seteo de parámetros de filtro
            query.setParameter("sexo", request.getSexo());
            query.setParameter("tipo", request.getNombrePaciente());
            query.setParameter("inicio", request.getFechaInicio());
            query.setParameter("fin", request.getFechaFin());
            query.setParameter("pagina", request.getPagina());
            query.setParameter("size", request.getSize());

            // 3. Obtención y mapeo de resultados
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.getResultList();
            long totalRegistros = 0;
            if (rows == null || rows.isEmpty()) {
            	return new DataPage<>(java.util.Collections.emptyList(), totalRegistros);
            }else {
            	totalRegistros=((Number) rows.get(0)[rows.get(0).length - 1]).longValue();
            }
            
            return new DataPage<>(mapper.fromRows(rows), totalRegistros);
    } catch (Exception e) {
        // Pasamos 'e' como tercer argumento para mantener la causa raíz
        throw new BusinessException("El servicio de consulta no está disponible actualmente.", e, 500);
    }
    }
}