package ru.popov.testjavacode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec200;
    private static ResponseSpecification responseSpec400;

    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;

        requestSpec = given()
                .header("Content-Type", "application/json");

        responseSpec200 = expect()
                .statusCode(200);

        responseSpec400 = expect()
                .statusCode(400);
    }

    @Test
    public void successfulRegistrationTest() {

        String requestBody = """
                    {
                        \"email\": \"eve.holt@reqres.in\",
                        \"password\": \"pistol\"
                    }
                """;

        Response response = given(requestSpec)
                .basePath("/register")
                .body(requestBody)
                .post();

        response.then()
                .spec(responseSpec200)
                .body("id", notNullValue())
                .body("token", notNullValue());

        assertNotNull(response.jsonPath().get("id"), "ID не должно быть null");
        assertNotNull(response.jsonPath().get("token"), "Token не должен быть null");
    }

    @Test
    public void registrationWithoutPasswordTest() {

        String requestBody = """
                    {
                        \"email\": \"sydney@fife\"
                    }
                """;

        Response response = given(requestSpec)
                .basePath("/register")
                .body(requestBody)
                .post();

        response.then()
                .spec(responseSpec400)
                .body("error", equalTo("Missing password"));

        assertEquals("Missing password", response.jsonPath().get("error"), "Сообщение об ошибке не соответствует ожидаемому");
    }
}
