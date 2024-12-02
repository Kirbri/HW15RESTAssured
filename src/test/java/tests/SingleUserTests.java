package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SingleUserTests {
 /*
    1. Make request to https://reqres.in/api/users/{0, 1, 2, !}
    2. Get response { "data": { "id": 2, "email": "janet.weaver@reqres.in", "first_name": "Janet" ...
    3. Check response
*/

    @Test
    void successfulSingleUser0Test() {
        given()
                .log().uri()
                .get("https://reqres.in/api/users/0")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }

    @Test
    void successfulSingleUser1Test() {
        get("https://reqres.in/api/users/1")
                .then()
                .statusCode(200)
                .body("data.id", is(1))
                .body("data.email", is("george.bluth@reqres.in"))
                .body("data.first_name", is("George"))
                .body("data.last_name", is("Bluth"))
                .body("data.avatar", is("https://reqres.in/img/faces/1-image.jpg"))
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }

    @Test
    void successfulSingleUser2Test() {
        get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }

    @Test
    void successfulSingleUser13Test() {
        given()
                .log().uri()
                .get("https://reqres.in/api/users/!")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }
}