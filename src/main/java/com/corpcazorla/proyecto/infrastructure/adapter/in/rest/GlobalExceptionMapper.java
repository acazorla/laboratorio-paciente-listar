package com.corpcazorla.proyecto.infrastructure.adapter.in.rest;

import org.jboss.logging.Logger; // Logger de Quarkus/JBoss
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response;
import java.util.UUID;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.ApiResponse;
import com.corpcazorla.proyecto.infrastructure.adapter.in.dto.Meta;

//import java.util.Collections;
//import org.hibernate.exception.SQLGrammarException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);
    @Override
    public Response toResponse(Throwable exception) {
    	// 1. Generar ID único de trazabilidad
        String traceId = UUID.randomUUID().toString();
        
        // 2. LOGUEO PROFESIONAL: 
        // Registramos el error REAL en la consola del servidor con su TraceID.
        LOG.errorf("Error Crítico [TraceID: %s] - Detalle Técnico: %s", traceId, exception.getMessage(), exception);

        // 3. Construcción de respuesta estandarizada
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Error en la comunicación con los servicios de datos.");
        errorResponse.setData(new ArrayList<>());
        
        Meta meta = new Meta();
        meta.setTraceId(traceId);
        meta.setTimestamp(OffsetDateTime.now().toString());
        meta.setVersion("v1");
        errorResponse.setMeta(meta);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }
}
