package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AlterarLivroPage extends BasePage{

    public Response alterarLivroPorID(String livroJson, String id) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("api-key","postmanrulz")
                .body(livroJson)
                .when()
                .patch("/books/"+ id);
    }
}
