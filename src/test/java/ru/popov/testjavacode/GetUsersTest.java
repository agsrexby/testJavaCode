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

public class GetUsersTest {
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
    void getUserListAndValidateEmails() {
        // Выполнение запроса для получения списка пользователей
        Response response = given(requestSpec)
                .basePath("/users")
                .queryParam("page", 1)
                .get();

        // Проверка статуса ответа
        response.then()
                .spec(responseSpec200)
                .body("data", notNullValue());

        // Проверка email пользователей
        response.jsonPath().getList("data.email", String.class).forEach(email -> {
            assertTrue(email.endsWith("@reqres.in"), "Email не имеет окончание @reqres.in: " + email);
        });
    }
}
