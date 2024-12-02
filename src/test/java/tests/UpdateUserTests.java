package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTests {
 /*
    1. Make request (PUT) to https://reqres.in/api/users/{2sq, 0, 1, 2, !}
    2. Get response { "data": { "id": 2, "email": "janet.weaver@reqres.in", "first_name": "Janet" ...
    3. Check response
*/

    @Test
    void successfulUpdateUser2sqTest() {
        String updateData = "{\"name\": \"test1\", \"job\": \"test1\"}";

        given()
                .body(updateData)
                .log().uri()
                .put("https://reqres.in/api/users/2sq")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);
    }

    @Test
    void successfulUpdateUser0Test() {
        String updateData = "{\"name\": \"test2\", \"job\": \"test2\"}";

        given()
                .body(updateData)
                .log().uri()
                .put("https://reqres.in/api/users/0")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);
    }

    @Test
    void successfulUpdateUser1Test() {
        String updateData = "{\"name\": \"test3\", \"job\": \"test3\"}";

        given()
                .body(updateData)
                .log().uri()
                .put("https://reqres.in/api/users/1")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);
    }
}