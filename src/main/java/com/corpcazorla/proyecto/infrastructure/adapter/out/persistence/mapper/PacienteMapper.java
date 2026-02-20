package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper;

import com.corpcazorla.proyecto.domain.model.Examen;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity.PacienteEntity;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity.ExamenEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {

    private PacienteMapper() {
        // Utility class
    }
    // ===============================
    // JPA ENTITY -> DOMAIN
    // ===============================
    public static Paciente fromEntity(PacienteEntity entity) {
        Paciente paciente = new Paciente();
        paciente.setId(entity.getId());
        paciente.setNombrePaciente(entity.getNombre());
        paciente.setSexo(entity.getSexo());

        if (entity.getExamenes() != null && !entity.getExamenes().isEmpty()) {
            List<Examen> listaExamenes = new ArrayList<>();
            
            for (ExamenEntity eEntity : entity.getExamenes()) {
                // Usamos el constructor que ya tienes definido (según imagen d702db)
                Examen examen = new Examen(
                    eEntity.getNombre(), 
                    eEntity.getFecha().toString(), 
                    eEntity.getRutaArchivo()
                );
                listaExamenes.add(examen);
            }
            paciente.setExamenes(listaExamenes);
        }
        return paciente;
    }

    public static List<Paciente> fromEntityList(List<PacienteEntity> entities) {
        return entities.stream()
                .map(PacienteMapper::fromEntity)
                .collect(Collectors.toList());
    }

    // ===============================
    // STORED PROCEDURE (Object[]) -> DOMAIN
    // ===============================
    public static Paciente fromRow(Object[] row) {
        Paciente p = new Paciente();

        p.setId((String) row[0]);
        p.setNombrePaciente((String) row[1]);
        p.setSexo((String) row[2]);
        
       // p.setExamenes((String) row[3]);

        //if (row[4] instanceof Timestamp ts) {
       //     p.setFechaExamen(ts.toLocalDateTime().toLocalDate());
       // } else if (row[4] instanceof LocalDate ld) {
        //    p.setFechaExamen(ld);
        //}

        //p.setRutaArchivo((String) row[5]);

        return p;
    }

    public static List<Paciente> fromRows(List<Object[]> rows) {
        List<Paciente> pacientes = new ArrayList<>();
        for (Object[] row : rows) {
            pacientes.add(fromRow(row));
        }
        return pacientes;
    }

    // ===============================
    // MOCK DATA -> DOMAIN
    // ===============================
    public static List<Paciente> mockList() {
        List<Paciente> lista = new ArrayList<>();
        // Si vas a agregar exámenes
        List<Examen> examenes = new ArrayList<>();
        examenes.add(new Examen("Hemograma", LocalDate.now().toString(), "\\172.20.14.18\\servicios\\MEDICINA OCUPACIONAL\\GENERAL_1432838.pdf"));
        examenes.add(new Examen("Orina completo", LocalDate.now().toString(), "\\172.20.14.18\\servicios\\MEDICINA OCUPACIONAL\\GENERAL_1432838.pdf"));
        examenes.add(new Examen("Radiografía tórax", LocalDate.now().toString(), "\\172.20.14.18\\servicios\\MEDICINA OCUPACIONAL\\GENERAL_1432838.pdf"));

        lista.add(new Paciente(
                "PAC-001",
                "Ana Martinez",
                "F",
                examenes
        ));

        //p1.setExamenes(examenes);
        lista.add(new Paciente(
                "PAC-002",
                "Luis Perez",
                "M",
                examenes
        ));

        return lista;
    }
    
}
