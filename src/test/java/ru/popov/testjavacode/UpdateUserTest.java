package ru.popov.testjavacode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateUserTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec200;

    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;

        requestSpec = given()
                .header("Content-Type", "application/json");

        responseSpec200 = expect()
                .statusCode(200);
    }

    @Test
    public void updateUserAndValidateUpdatedDate() {

        String requestBody = """
            {
                \"name\": \"morpheus\",
                \"job\": \"zion resident\"
            }
        """;

        Response response = given(requestSpec)
                .basePath("/users/2")
                .body(requestBody)
                .patch();

        response.then()
                .spec(responseSpec200)
                .body("updatedAt", notNullValue());

        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String updatedAt = response.jsonPath().getString("updatedAt");
        assertTrue(updatedAt.startsWith(currentDate), "Дата обновления не совпадает с текущей датой: " + updatedAt);
    }
}
