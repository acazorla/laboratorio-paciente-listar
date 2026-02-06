package com.corpcazorla.proyecto.infrastructure.adapter.in.rest;

import com.corpcazorla.proyecto.application.service.ListarExamenesPacienteService;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/laboratorio/pacientes")
@Produces(MediaType.APPLICATION_JSON)
public class PacienteController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST PacienteController";
    }
    
    @Inject
    ListarExamenesPacienteService service;
    @GET
    @Path("/listar")
    public Response listarPaciernteExamen(PacienteRequest request) {
  //  	List<Paciente> pacientes = service.ejecutar(request);
    	return Response.ok(service.ejecutar(request)).build();
    }
}
