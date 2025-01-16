package ru.popov.testjavacode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetUsersTest {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void getUserListAndValidateEmails() {
        // Выполнение запроса для получения списка пользователей
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/users")
                .queryParam("page", 1)
                .get();

        // Проверка статуса ответа
        response.then()
                .statusCode(200)
                .body("data", notNullValue());

        // Проверка email пользователей
        response.jsonPath().getList("data.email", String.class).forEach(email -> {
            assertTrue(email.endsWith("@reqres.in"), "Email не имеет окончание @reqres.in: " + email);
        });
    }
}
