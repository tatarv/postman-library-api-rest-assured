package pages;

import io.restassured.RestAssured;

public class BasePage {
    public BasePage() {
        RestAssured.baseURI = "https://library-api.postmanlabs.com";
    }
}
