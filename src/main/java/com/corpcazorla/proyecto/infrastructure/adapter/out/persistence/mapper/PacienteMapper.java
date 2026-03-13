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

@ApplicationScoped
public class PacienteMapper {

 // NUEVO MÉTODO: Convierte Dominio -> DTO de Respuesta
    public List<PacienteResponse> toResponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toResponse)
                .toList();
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
                    .toList();
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

                Examen examen = new Examen();
                examen.setNombre(eEntity.getNombre());
                examen.setFecha(eEntity.getFecha().toString());
                examen.setArchivo(eEntity.getRutaArchivo());
                listaExamenes.add(examen);
            }
            paciente.setExamenes(listaExamenes);
        }
        return paciente;
    }

    public static List<Paciente> fromEntityList(List<PacienteEntity> entities) {
        return entities.stream()
                .map(PacienteMapper::fromEntity)
                .toList();
    }

    // ===============================
    // STORED PROCEDURE (Object[]) -> DOMAIN
    // ===============================
    public static Paciente fromRow(Object[] row) {
        Paciente p = new Paciente();

        p.setId((String) row[0]);
        p.setNombrePaciente((String) row[1]);
        p.setSexo((String) row[2]);
 
        return p;
    }


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
                p.setFechaNacimiento(safeString(row, 4, "N/A"));
                p.setExamenes(new ArrayList<>());
                return p;
            });

            // 3. Validación de existencia de examen en la fila
            if (row.length > 5 && row[5] != null) {
                Examen examen = new Examen();
                examen.setNombre(row[5].toString());
                examen.setFecha(safeString(row, 6, "N/A"));
                examen.setArchivo(safeString(row, 7, "URL_NO_DISPONIBLE"));
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
        Examen examen = new Examen();
        examen.setNombre("Hemograma");
        examen.setFecha(LocalDate.now().toString());
        examen.setArchivo("GENERAL_1432838.pdf");
        examenes.add(examen);
        examen.setNombre("Orina completo");
        examen.setFecha(LocalDate.now().toString());
        examen.setArchivo("GENERAL_1432839.pdf");
        examenes.add(examen);
        examen.setNombre("Radiografía tórax");
        examen.setFecha(LocalDate.now().toString());
        examen.setArchivo("GENERAL_1432840.pdf");
        examenes.add(examen);
        Paciente paciente = new Paciente();
        paciente.setId("PAC-001");
        paciente.setNombrePaciente("Ana Martinez");
        paciente.setSexo("F");
        paciente.setFechaAtencion("2026-01-20");
        paciente.setFechaNacimiento("1983-02-23");
        paciente.setExamenes(examenes);
        
        lista.add(paciente);
        paciente.setId("PAC-002");
        paciente.setNombrePaciente("Luis Perez");
        paciente.setSexo("M");
        paciente.setFechaAtencion("2026-02-05");
        paciente.setFechaNacimiento("1985-05-23");
        paciente.setExamenes(examenes);

        lista.add(paciente);

        return lista;
    }
    
}
