package ru.popov.testjavacode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class DeleteUserTest {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void testDeleteUser() {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath("/users/2")
                .delete();
        response.then()
                .statusCode(204);
    }
}
