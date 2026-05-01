package com.corpcazorla.complaints;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testRegisterComplaintEndpoint() {
        // Creamos un JSON que cumpla con las validaciones de ComplaintsRequest
        String body = """
            {
                "documentTypeId": 1,
                "documentNumber": "12345678",
                "firstName": "Juan",
                "lastName": "Perez",
                "middleName": "Gomez",
                "email": "juan.perez@example.com",
                "phoneNumber": "987654321",
                "countryId": 1,
                "locationLevel2Id": 10,
                "locationLevel3Id": 101,
                "detailedAddress": "Av. Principal 123",
                "postalCode": "15046",
                "hasRepresentative": false,
                "branchId": 1,
                "complaintType": "R",
                "categoryId": 2,
                "currencyId": 1,
                "claimedAmount": 1500.00,
                "incidentDate": "2026-04-24",
                "complaintDescription": "Falla técnica en equipo",
                "consumerRequest": "Cambio de producto",
                "createdBy": "%s",
                "evidenceList": []
            }
            """.formatted(UUID.randomUUID().toString());

        given()
          .contentType(ContentType.JSON)
          .body(body)
        .when()
          .post("/api/customers/complaints/register") // Cambio a POST
        .then()
          .statusCode(200) // Esperamos 201 Created
          .body("trackingCode", notNullValue()) // Validamos que el SP devolvió el código
          .body("complaintId", notNullValue());
    }

    @Test
    void testRegisterComplaintValidationFail() {
        // Test para verificar que las validaciones de @Size y @NotNull funcionan
        String invalidBody = "{\"firstName\": \"\"}"; // Body incompleto y vacío

        given()
          .contentType(ContentType.JSON)
          .body(invalidBody)
        .when()
          .post("/api/customers/complaints/register")
        .then()
          .statusCode(400); // Bad Request por fallos de validación
    }
}