package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class ListUsersTests {

    /*
    1. Make request to https://reqres.in/api/users?page=2
    2. Get response { "page": 2, "per_page": 6, "total": 12 ...
    3. Check response
*/

    @Test
    void successfulLisUsersTest() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2))
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12))
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }
}
