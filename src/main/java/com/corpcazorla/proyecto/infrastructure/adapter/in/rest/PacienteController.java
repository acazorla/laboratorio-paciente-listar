package com.corpcazorla.proyecto.infrastructure.adapter.in.rest;

import java.util.List;

import com.corpcazorla.proyecto.application.service.ListarExamenesPacienteService;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.PacienteRequest;
import com.corpcazorla.proyecto.domain.model.Paciente;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    	System.out.println("El JSON que llegó");
        return "Hello from Quarkus REST PacienteController";
    }
    
    @Inject
    ListarExamenesPacienteService service;
    @POST
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPaciernteExamen(PacienteRequest request) {
    	System.out.println("El JSON que llegó es: " + request.toString());
    	return Response.ok(service.ejecutar(request)).build();
    }

}
