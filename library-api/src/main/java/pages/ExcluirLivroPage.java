package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExcluirLivroPage extends BasePage{
    public Response deletarLivroPorID(String id) {
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("api-key","postmanrulz")
                .when()
                .delete("/books/"+ id);
    }
}
