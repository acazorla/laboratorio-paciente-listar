package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence;

/*
 * import com.corpcazorla.proyecto.domain.model.Paciente; import
 * com.corpcazorla.proyecto.domain.ports.out.PacienteRepositoryPort; import
 * jakarta.enterprise.context.ApplicationScoped; import jakarta.inject.Inject;
 * import jakarta.persistence.EntityManager; import
 * jakarta.persistence.StoredProcedureQuery;
 * 
 * import java.time.LocalDate; import java.util.ArrayList; import
 * java.util.List;
 * 
 * @ApplicationScoped public class PacienteJpaAdapter implements
 * PacienteRepositoryPort {
 * 
 * @Inject EntityManager entityManager;
 * 
 * @Override public List<Paciente> buscarConFiltros( String sexo, String
 * tipoExamen, LocalDate fechaInicio, LocalDate fechaFin, int pagina, int size)
 * {
 * 
 * StoredProcedureQuery query = entityManager
 * .createStoredProcedureQuery("sp_listar_pacientes_examen");
 * 
 * query.registerStoredProcedureParameter("sexo", String.class,
 * jakarta.persistence.ParameterMode.IN);
 * query.registerStoredProcedureParameter("tipoExamen", String.class,
 * jakarta.persistence.ParameterMode.IN);
 * query.registerStoredProcedureParameter("fechaInicio", LocalDate.class,
 * jakarta.persistence.ParameterMode.IN);
 * query.registerStoredProcedureParameter("fechaFin", LocalDate.class,
 * jakarta.persistence.ParameterMode.IN);
 * 
 * query.setParameter("sexo", sexo); query.setParameter("tipoExamen",
 * tipoExamen); query.setParameter("fechaInicio", fechaInicio);
 * query.setParameter("fechaFin", fechaFin);
 * 
 * List<Object[]> result = query.getResultList();
 * 
 * List<Paciente> pacientes = new ArrayList<>();
 * 
 * for (Object[] row : result) { Paciente p = new Paciente(); p.setId((String)
 * row[0]); p.setNombre((String) row[1]); p.setSexo((String) row[2]);
 * p.setNombreExamen((String) row[3]); p.setFechaExamen(row[4].toString());
 * 
 * pacientes.add(p); }
 * 
 * return pacientes; }
 * 
 * }
 */