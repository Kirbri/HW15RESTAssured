package helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureListener {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplate() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}
