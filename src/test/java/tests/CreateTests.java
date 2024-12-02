package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateTests {

    /*
1. Make request (POST) to https://reqres.in/api/users/{2sq, 0, 1, 2, !}
with body { "name": "morpheus", "job": "leader" }
2. Get response { "name": "morpheus", "job": "leader" ...
3. Check response
*/

    @Test
    void successfulCreateTest() {
        String authData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void successfulCreateNoDataTest() {
        String authData = "";

        given()
                .body(authData)
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(201);
    }
}
