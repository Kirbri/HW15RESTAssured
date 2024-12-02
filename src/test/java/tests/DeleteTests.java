package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteTests {

    /*
1. Make request (DELETE) to https://reqres.in/api/users{2, _, 19021}
2. Check response code
*/

    @Test
    void successfulDelete2Test() {
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .log().status()
                .statusCode(204);
    }

    @Test
    void successfulDeleteTest() {
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reqres.in/api/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(204);
    }

    @Test
    void successfulDelete19021Test() {
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reqres.in/api/users/19021")
                .then()
                .log().body()
                .log().status()
                .statusCode(204);
    }
}
