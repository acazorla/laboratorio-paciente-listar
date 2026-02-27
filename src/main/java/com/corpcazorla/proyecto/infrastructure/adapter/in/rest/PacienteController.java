package com.corpcazorla.proyecto.infrastructure.adapter.in.rest;

import com.corpcazorla.proyecto.application.service.ListarExamenesPacienteService;
import com.corpcazorla.proyecto.domain.model.Paciente;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.*;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;

//import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
import java.util.List;
@Path("/api/laboratorio/pacientes")
@Produces(MediaType.APPLICATION_JSON)
public class PacienteController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST PacienteController";
    }
    
    //@Inject
    private final ListarExamenesPacienteService service;
    //@Inject
    private final PacienteMapper mapper;
 // Inyección por constructor: Limpio, seguro y sin errores de Sonar
    public PacienteController(ListarExamenesPacienteService service, PacienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @POST
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<List<PacienteResponse>> listarPacienteExamen(PacienteRequest request) {
        // 1. Lógica de negocio (Dominio)
        List<Paciente> pacientesDominio = service.ejecutar(request);
        // 2. Transformación a DTO (Usa el nuevo método del mapper)
        List<PacienteResponse> respuestaDto = mapper.toResponseList(pacientesDominio);
        // 3. Respuesta estandarizada
        Pagination pagination = new Pagination(1, respuestaDto.size(),1,1);
        return new ApiResponse<>(respuestaDto, pagination);
    }

}
