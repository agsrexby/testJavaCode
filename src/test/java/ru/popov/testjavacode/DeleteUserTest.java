package ru.popov.testjavacode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec204;

    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;

        requestSpec = given()
                .header("Content-Type", "application/json");

        responseSpec204 = expect()
                .statusCode(204);
    }

    @Test
    void DeleteUserTest() {
        Response response = given(requestSpec)
                .basePath("/users/2")
                .delete();

        response.then()
                .spec(responseSpec204);
    }
}