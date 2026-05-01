package com.corpcazorla.complaints;

import com.corpcazorla.complaints.application.service.RegisterComplaintServiceImpl;
import com.corpcazorla.complaints.domain.model.DataPage;
import com.corpcazorla.complaints.domain.model.Complaint;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@QuarkusTest
class ComplaintControllerTest {

	@InjectMock
    RegisterComplaintServiceImpl service; // Mockeamos el servicio que inyectas por constructor
	
    @Test
    void testRegisterComplaintSuccess() {
        // 1. Crear datos de prueba basados en tu modelo 'Paciente.java'
        Complaint mockPaciente = new Complaint();
        mockPaciente.setComplaintId("30626C25-6F3E-F111-9F8D-005056A122AA"); 
        // Agrega los setters necesarios según tu entidad de dominio
        // 2. Simular la respuesta paginada usando tu clase 'DataPage.java'
        DataPage<Complaint> mockPage = new DataPage<>(
            List.of(mockPaciente), 
            1L // totalElements
        );
        // 3. Programar el Mock
        when(service.registerComplaint(any())).thenReturn(mockPage);
        // 4. Ejecución del Test
        given()
          .contentType("application/json")
          .body("{\"documentTypeId\": 1, \"documentNumber\": \"41643957\", \"firstName\": \"Alex\", \"lastName\": \"Cazorla\", \"email\": \"alex@example.com\", \"phoneNumber\": \"987654321\", \"countryId\": 1, \"locationLevel2Id\": 10, \"detailedAddress\": \"Calle Real 123\", \"hasRepresentative\": false, \"branchId\": 1, \"complaintType\": \"R\", \"categoryId\": 1, \"currencyId\": 1, \"claimedAmount\": 100.0, \"incidentDate\": \"2026-04-24\", \"complaintDescription\": \"Descripcion de prueba\", \"consumerRequest\": \"Cambio\", \"createdBy\": \"d02b0fd1-6c3e-f111-9f8d-005056a122aa\"}")
        .when()
          .post("/api/customers/complaints/register")
        .then()
          .statusCode(200)
          .body("success", is(true))
          .body("data[0].complaintId", is("30626C25-6F3E-F111-9F8D-005056A122AA"));
    }
}
