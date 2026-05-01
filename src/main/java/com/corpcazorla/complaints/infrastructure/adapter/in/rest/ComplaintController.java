package com.corpcazorla.complaints.infrastructure.adapter.in.rest;

import com.corpcazorla.complaints.application.service.RegisterComplaintService;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.*;
import com.corpcazorla.complaints.infrastructure.adapter.out.persistence.mapper.ComplaintMapper;

import jakarta.ws.rs.Consumes;
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

@Path("/api/customers/complaints")
@Tag(name = "Complaints/Reclamos", description = "Claims registration/Registro de reclamos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComplaintController {

    private final RegisterComplaintService service;

    private final ComplaintMapper mapper;
 // Inyección por constructor: Limpio, seguro y sin errores de Sonar
    public ComplaintController(RegisterComplaintService service, ComplaintMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @ConfigProperty(name = "proyecto.api.version")
    String apiVersion; 
    @POST
    @Path("/register")
    @APIResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @APIResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    @APIResponse(responseCode = "500", description = "Error interno del servidor")
    public Response registerComplaint(@Valid ComplaintsRequest request) {
    	var complaintsDomain = service.registerComplaint(request);
        List<ComplaintResponse> respuestaDto = mapper.toResponseList(complaintsDomain.getData());
        Pagination pagination = new Pagination(1, 1,1,1);
        return Response.ok(new ApiResponse<>(respuestaDto, pagination)).build();
    }
}
