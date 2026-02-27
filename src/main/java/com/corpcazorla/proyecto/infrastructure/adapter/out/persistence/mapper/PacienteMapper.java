package com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper;

import com.corpcazorla.proyecto.domain.model.Examen;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity.PacienteEntity;

import jakarta.enterprise.context.ApplicationScoped;

import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.*;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.entity.ExamenEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class PacienteMapper {

//    private PacienteMapper() {
        // Utility class
//    }
 // NUEVO MÉTODO: Convierte Dominio -> DTO de Respuesta
    public List<PacienteResponse> toResponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    public PacienteResponse toResponse(Paciente paciente) {
        PacienteResponse dto = new PacienteResponse();
        dto.setId(paciente.getId());
        dto.setNombrePaciente(paciente.getNombrePaciente());
        dto.setSexo(paciente.getSexo());
        dto.setFechaAtencion(paciente.getFechaAtencion());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        // Mapea aquí los exámenes si tu PacienteResponse los tiene
        if (paciente.getExamenes() != null) {
            List<ExamenResponse> examenesDto = paciente.getExamenes().stream()
                    .map(this::toExamenResponse)
                    .collect(Collectors.toList());
            dto.setExamenes(examenesDto);
        }
        return dto;
    }
 // Método auxiliar para mapear un solo examen
    private ExamenResponse toExamenResponse(Examen examen) {
        ExamenResponse examenDto = new ExamenResponse();
        examenDto.setNombre(examen.getNombre());
        examenDto.setFecha(examen.getFecha()); // Asegúrate de que los tipos coincidan (String o LocalDate)
        examenDto.setArchivo(examen.getArchivo());
        return examenDto;
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

    //public static List<Paciente> fromRows(List<Object[]> rows) {
    //    List<Paciente> pacientes = new ArrayList<>();
    //    for (Object[] row : rows) {
    //        pacientes.add(fromRow(row));
    //    }
    //    return pacientes;
    //}
    public List<Paciente> fromRows(List<Object[]> rows) {
        // Usamos LinkedHashMap para mantener el orden de la base de datos
        Map<String, Paciente> pacienteMap = new LinkedHashMap<>();

        for (Object[] row : rows) {
            // 1. Validación de fila nula o vacía
            if (row == null || row.length < 1 || row[0] == null) continue;

            String pacienteId = row[0].toString();
            
            // 2. Agrupación segura del Paciente
            Paciente paciente = pacienteMap.computeIfAbsent(pacienteId, id -> {
                Paciente p = new Paciente();
                p.setId(id);
                p.setNombrePaciente(safeString(row, 1, "Sin Nombre"));
                p.setSexo(safeString(row, 2, "N/A"));
                p.setFechaAtencion(safeString(row, 3, "N/A"));
                p.setExamenes(new ArrayList<>());
                return p;
            });

            // 3. Validación de existencia de examen en la fila
            // Si la columna 3 (nombre examen) es nula, esta fila no tiene examen
            if (row.length > 5 && row[5] != null) {
                Examen examen = new Examen(
                    row[3].toString(), 
                    safeString(row, 6, LocalDate.now().toString()), // Fecha por defecto hoy
                    safeString(row, 7, "URL_NO_DISPONIBLE")         // Ruta por defecto
                );
                paciente.getExamenes().add(examen);
            }
        }
        return new ArrayList<>(pacienteMap.values());
    }

    // Método auxiliar para evitar repetición de código y nulos
    private String safeString(Object[] row, int index, String defaultValue) {
        return (row.length > index && row[index] != null) ? row[index].toString() : defaultValue;
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
                "F","2026-01-20","1983-02-23",
                examenes
        ));

        //p1.setExamenes(examenes);
        lista.add(new Paciente(
                "PAC-002",
                "Luis Perez",
                "M","2026-02-05","1985-05-23",
                examenes
        ));

        return lista;
    }
    
}
