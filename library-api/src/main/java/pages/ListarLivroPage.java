package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ListarLivroPage extends BasePage{
    public Response listarLivros(){
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("api-key","postmanrulz")
                .when()
                .get("/books");
    }
    public Response listarLivrosPorID(String id){
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("api-key","postmanrulz")
                .when()
                .get("/books/"+id);
    }
}
