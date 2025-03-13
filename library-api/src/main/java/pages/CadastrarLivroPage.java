package pages;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CadastrarLivroPage extends BasePage{

    public Response cadastrarLivro(String livroJson) {
        return RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .header("api-key","postmanrulz")
                    .body(livroJson)
                .when()
                    .post("/books");
    }
}
