package com.corpcazorla.complaints.infrastructure.adapter.in.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger; // Logger de Quarkus/JBoss
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response;
import java.util.UUID;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ApiResponse;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.Meta;
import com.corpcazorla.complaints.infrastructure.adapter.out.persistence.BusinessException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @ConfigProperty(name = "proyecto.api.version")
    String apiVersion;
	private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);
    @Override
    public Response toResponse(Throwable exception) {
    	String traceId = UUID.randomUUID().toString();
        
        // Valores por defecto para errores no controlados (500)
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        String userMessage = "Error en la comunicación con los servicios de datos.";

        // Identificamos si es un error controlado por nosotros
        if (exception instanceof BusinessException be) {
            status = Response.Status.fromStatusCode(be.getCode());
            userMessage = be.getMessage();
            LOG.warnf("Excepción de Negocio [TraceID: %s]: %s", traceId, userMessage);
        } else {
            // Si es un error técnico real (NullPointer, SQL Error, etc.)
            LOG.errorf("Error Crítico [TraceID: %s] - Detalle: %s", traceId, exception.getMessage(), exception);
        }

        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setSuccess(false);
        errorResponse.setMessage(userMessage);
        errorResponse.setData(new ArrayList<>());
        
        Meta meta = new Meta();
        meta.setTraceId(traceId);
        meta.setTimestamp(OffsetDateTime.now().toString());
        meta.setVersion(apiVersion);
        errorResponse.setMeta(meta);

        return Response.status(status).entity(errorResponse).build();
    }
}
