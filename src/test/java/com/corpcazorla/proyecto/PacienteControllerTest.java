package com.corpcazorla.proyecto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; // Importación clave corregida

@QuarkusTest
class PacienteControllerTest {

    @Test
    void testListarPacientesSuccess() {
        String requestBody = """
            {
                "sexo": "F",
                "tipoExamen": "BIOQUIMICA",
                "fechaInicio": "2024-01-01",
                "fechaFin": "2024-12-31",
                "pagina": 1,
                "size": 10
            }
            """;

        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body(requestBody)
          .when().post("/api/laboratorio/pacientes/listar")
          .then()
             .statusCode(200)
             .body("success", is(true))
             .body("data", notNullValue())
             .body("data[0].nombrePaciente", notNullValue()) // Valida que el primer paciente tenga nombre
             .body("meta.version", is("v1"));
    }
}
