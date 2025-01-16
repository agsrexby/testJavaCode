package ru.popov.testjavacode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateUserTest {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void updateUserAndValidateUpdatedDate() {

        String requestBody = """
            {
                \"name\": \"morpheus\",
                \"job\": \"zion resident\"
            }
        """;

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/users/2")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .patch();

        response.then()
                .statusCode(200)
                .body("updatedAt", notNullValue());

        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String updatedAt = response.jsonPath().getString("updatedAt");
        assertTrue(updatedAt.startsWith(currentDate), "Дата обновления не совпадает с текущей датой: " + updatedAt);
    }
}
