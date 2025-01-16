package ru.popov.testjavacode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTests {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void successfulRegistrationTest() {
        String requestBody = """
            {
                \"email\": \"eve.holt@reqres.in\",
                \"password\": \"pistol\"
            }
        """;

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/register")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        // Проверка статуса ответа и структуры ответа
        response.then()
                .statusCode(200)
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

        // Выполнение запроса
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/register")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post();

        // Проверка статуса ответа
        response.then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));

        assertEquals("Missing password", response.jsonPath().get("error"), "Сообщение об ошибке не соответствует ожидаемому");
    }
}
