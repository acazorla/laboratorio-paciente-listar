package com.corpcazorla.proyecto.infrastructure.adapter.in.rest;

import com.corpcazorla.proyecto.application.service.ListarExamenesPacienteService;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.*;
import com.corpcazorla.proyecto.infrastructure.adapter.out.persistence.mapper.PacienteMapper;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/laboratorio/pacientes")
@Tag(name = "Pacientes", description = "Operaciones relacionadas con la gestión de pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	String rev=revertir("mc|sw");
        return "Hello from Quarkus REST PacienteController:"+ rev;
    }
    
    public static String revertir(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            char original = (char) (c - (i + 1));
            resultado.append(original);
        }

        return resultado.toString();
    }
    private final ListarExamenesPacienteService service;

    private final PacienteMapper mapper;
 // Inyección por constructor: Limpio, seguro y sin errores de Sonar
    public PacienteController(ListarExamenesPacienteService service, PacienteMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @ConfigProperty(name = "proyecto.api.version")
    String apiVersion; 
    @POST
    @Path("/listar")
    @APIResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @APIResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    @APIResponse(responseCode = "500", description = "Error interno del servidor")
    public Response listarPacienteExamen(@Valid PacienteRequest request) {
    	var pacientesDominio = service.listarPaciente(request);
        List<PacienteResponse> respuestaDto = mapper.toResponseList(pacientesDominio.getData());
        int totalPages = (int) Math.ceil((double) pacientesDominio.getTotalElements() / request.getSize());
        Pagination pagination = new Pagination(request.getPagina(), request.getSize(),pacientesDominio.getTotalElements(),totalPages);
        return Response.ok(new ApiResponse<>(respuestaDto, pagination)).build();
        
    }

}
