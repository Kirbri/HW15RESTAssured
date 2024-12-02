package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class StatusTests {

/*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response {  "total": 20,  "used": 0,  "queued": 0,  "pending": 0, ...}
    3. Check total is 20
*/

    @Test
    void checkTotal20Test() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithResponseLogsTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithLogsTest() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLogsTest() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithStatusLogsTest() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChrome100Test() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("browsers.chrome", hasKey("100.0"));
    }

    @Test
    void checkChrome100Firefox123Opera107Test() {
        given()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("browsers.chrome", hasKey("100.0"))
                .body("browsers.firefox", hasKey("123.0"))
                .body("browsers.opera", hasKey("107.0"));
    }
}