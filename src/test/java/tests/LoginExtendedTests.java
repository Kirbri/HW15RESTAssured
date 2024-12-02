package tests;

import io.restassured.http.ContentType;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendedTests {
    /*
     1. Make request (POST) to https://reqres.in/api/login
     with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
     2. Get response { "token" : "QpwL5tke4Pnpja7X4" }
     3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
    */


    @Test
    void successfulLoginTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()
                .body(authData)
                .contentType(ContentType.JSON)
                .log().uri()
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
                //.body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulLoginBadPracticeTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

}
