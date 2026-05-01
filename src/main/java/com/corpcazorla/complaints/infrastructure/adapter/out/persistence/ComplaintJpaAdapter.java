package com.corpcazorla.complaints.infrastructure.adapter.out.persistence;

import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import com.corpcazorla.complaints.domain.ports.out.ComplaintRepositoryPort;
import com.corpcazorla.complaints.infrastructure.adapter.in.dto.ComplaintsRequest;
import com.corpcazorla.complaints.infrastructure.adapter.out.persistence.mapper.ComplaintMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

@ApplicationScoped
@Default // Asegura que este sea el principal
@jakarta.annotation.Priority(10) // Un número alto le da más importancia que al Mock
public class ComplaintJpaAdapter implements ComplaintRepositoryPort {

    private final EntityManager entityManager;
    private final ComplaintMapper mapper;

    public ComplaintJpaAdapter(EntityManager entityManager, ComplaintMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    @Transactional // Asegura la gestión de la transacción
    public DataPage<Complaint> register(ComplaintsRequest request) {
    	try {
    		// 1. Convertir la lista de objetos a un String JSON
    		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

    		if (request.getEvidenceList() != null) {
    		    for (var ev : request.getEvidenceList()) {
    		        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
    		            .add("evidenceTypeId", ev.getEvidenceTypeId())
    		            .add("originalName", ev.getOriginalName())
    		            .add("storageFileName", ev.getStorageFileName())
    		            .add("fileExtension", ev.getFileExtension())
    		            .add("mimeType", ev.getMimeType())
    		            .add("fileSizeBytes", ev.getFileSizeBytes())
    		            .add("accessUrl", ev.getAccessUrl())
    		            .add("verificationHash", ev.getVerificationHash());
    		        arrayBuilder.add(objBuilder);
    		    }
    		}

    		String evidenceJson = arrayBuilder.build().toString();
    		// 1. Definición de la consulta nativa
            Query query = entityManager.createNativeQuery("EXEC complaints.usp_complaint_register_v3 :document_type_id, :document_number, "
            		+ ":first_name, :last_name, :middle_name, :email, :phone_number, :country_id, :location_level_2_id, :location_level_3_id, :location_level_4_id, :detailed_address, "
            		+ ":postal_code, :has_representative, :representative_name, :branch_id, :complaint_type, :category_id, :currency_id, :claimed_amount,"
            		+ " :incident_date, :description, :consumer_request, :created_by, :evidence_list ");
            // 2. Seteo de parámetros

            query.setParameter("document_type_id", request.getDocumentTypeId()); //@document_type_id = 1,                 -- DNI / Cedula
            query.setParameter("document_number", request.getDocumentNumber());//@document_number = '12345678',
            query.setParameter("first_name", request.getFirstName());//@first_name = 'Juan',
            query.setParameter("last_name", request.getLastName());//@last_name = 'Perez',
            query.setParameter("middle_name", request.getMiddleName()); //@middle_name = 'Gomez',
            query.setParameter("email", request.getEmail());//@email = 'juan.perez@email.com',
            query.setParameter("phone_number", request.getPhoneNumber());//@phone_number = '+51987654321',
            //-- Ubicación
            query.setParameter("country_id", request.getCountryId());//@country_id = 1,                       -- Peru / España / etc.
            query.setParameter("location_level_2_id", request.getLocationLevel2Id());//@location_level_2_id = 10,             -- Departamento / Provincia
            query.setParameter("location_level_3_id", request.getLocationLevel3Id());//@location_level_3_id = 101,            -- Ciudad / Distrito
            query.setParameter("location_level_4_id", request.getLocationLevel4Id());//@location_level_4_id = NULL,
            query.setParameter("detailed_address", request.getDetailedAddress());//@detailed_address = 'Av. Siempre Viva 742',
            query.setParameter("postal_code", request.getPostalCode());//@postal_code = '15046',
            //-- Representación
            query.setParameter("has_representative", request.getHasRepresentative());//@has_representative = 0,
            query.setParameter("representative_name", request.getRepresentativeName());//@representative_name = NULL,
            //-- Detalles del Reclamo
            query.setParameter("branch_id", request.getBranchId());//@branch_id = 1,                        -- Tienda Central
            query.setParameter("complaint_type", request.getComplaintType());//@complaint_type = 'R',                 -- 'R' de Reclamo
            query.setParameter("category_id", request.getCategoryId());//@category_id = 2,                      -- 'Electronica'
            query.setParameter("currency_id", request.getCurrencyId());//@currency_id = 1,                      -- 'Soles' / 'Euros'
            query.setParameter("claimed_amount", request.getClaimedAmount());//@claimed_amount = 1500.50,
            query.setParameter("incident_date", request.getIncidentDate());//@incident_date = '2026-04-20',
            query.setParameter("description", request.getComplaintDescription());//@description = 'El televisor que compré presenta fallas en los píxeles tras 2 días de uso.',
            query.setParameter("consumer_request", request.getConsumerRequest());//@consumer_request = 'Solicito el cambio de equipo o devolución del dinero.',
            //-- Auditoría y TVP
            query.setParameter("created_by", request.getCreatedBy());//@created_by = 'D02B0FD1-6C3E-F111-9F8D-005056A122AA', -- UUID del usuario de la App
            query.setParameter("evidence_list", evidenceJson);

            // 3. Obtención y mapeo de resultados
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.getResultList();
         // Estándar DataPage: Si es un registro exitoso, el total es el tamaño de la lista (normalmente 1)
            
            if (rows == null || rows.isEmpty()) {
            	return new DataPage<>(java.util.Collections.emptyList(), 0L);
            }
            
            return new DataPage<>(mapper.fromRows(rows),rows.size());
    } catch (PersistenceException e) {
            // Captura errores de SQL Server (RAISERROR / THROW)
            String rootMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
            throw new BusinessException("Error en base de datos: " + rootMsg, e, 409);

    } catch (Exception e) {
        // Pasamos 'e' como tercer argumento para mantener la causa raíz
        throw new BusinessException("El servicio no está disponible actualmente.", e, 500);
    }
    	
    }
}