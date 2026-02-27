package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence;

import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort;
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
    public List<Paciente> buscarConFiltros(String sexo, String tipoExamen, String fechaInicio, String fechaFin, int pagina, int size) {
    	try {
    	// Llamada nativa al procedimiento almacenado de SQL Server
        Query query = entityManager.createNativeQuery("EXEC OCUPACIONAL.usp_listar_pacientes_examen :sexo, :tipo, :inicio, :fin");
        
        // Seteo de parámetros
        query.setParameter("sexo", sexo);
        query.setParameter("tipo", tipoExamen);
        query.setParameter("inicio", fechaInicio);
        query.setParameter("fin", fechaFin);

        // Obtenemos la lista de objetos planos (Object[])
        //@SuppressWarnings("unchecked")
        //List<Object[]> rows = query.getResultList();
        
        // Usamos tu mapper para transformar de filas SQL a objetos de Dominio
       // return mapper.fromRows(rows);
        //return mapper.fromRows(query.getResultList());
     // Cast seguro para evitar el warning de "Type safety"
        @SuppressWarnings("unchecked")
        List<Object[]> rows = (List<Object[]>) query.getResultList();
     // Es buena práctica validar nulidad antes del mapeo
        if (rows == null) {
            return java.util.Collections.emptyList();
        }
        return mapper.fromRows(rows);
    } catch (Exception e) {
        // Logueamos el error real para el desarrollador
        //Log.error("Error al ejecutar SP: " + e.getMessage());
        // Lanzamos una excepción que el Mapper capturará para el usuario
        throw new BusinessException("El servicio de consulta no está disponible actualmente.", 500);
    }
    }
}