package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LoginTests {
    /*
     1. Make request (POST) to https://reqres.in/api/login
     with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
     2. Get response { "token" : "QpwL5tke4Pnpja7X4" }
     3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
    */


    @Test
    void successfulLoginTest() {
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

    @Test
    void unsuccessfulMissingPasswordTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"\"}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void unsuccessfulNoCurrentDataTest() {
        String authData = "1@#!}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400);
    }

    @Test
    void unsuccessfulMissingEmailOrUsernameTest() {
        String authData = "{\"password\": \"123\"}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void unsuccessfulUserNotFound400Test() {
        String authData = "{\"email\": \"dfsdfsddf\", \"password\": \"12312312312\"}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    void unsuccessfulLoginNoJSON400Test() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .log().uri()
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    void unsuccessfulLoginNoData400Test() {
        String authData = "";

        given()
                .body(authData)
                .log().uri()
            .when()
                .post("https://reqres.in/api/login")
            .then()
                .log().body()
                .log().status()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void unsuccessfulLogin415Test() {
        given()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .log().status()
                .statusCode(415);
    }
}
