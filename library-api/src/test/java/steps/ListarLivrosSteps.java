package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.ListarLivroPage;
import static org.junit.Assert.*;

public class ListarLivrosSteps {
    private ListarLivroPage listarLivroPage;
    private Response response;
    private String idLivro;

    @Given("que eu quero listar os livros que estão cadastrados na biblioteca")
    public void queEuQueroListarOsLivrosQueEstãoCadastradosNaBiblioteca() {
        listarLivroPage = new ListarLivroPage();
    }
    @Then("todos os livros que estão cadastrados devem ser retornados.")
    public void todosOsLivrosQueEstãoCadastradosDevemSerRetornados() {
        String responseBody = response.getBody().asString();
        assertNotNull(responseBody);
        System.out.println("Livros retornados");

       String[] keywords = { "id", "title", "author", "genre", "yearPublished", "checkedOut", "isPermanentCollection", "createdAt" };

        for (String keyword : keywords) {
            assertTrue("A resposta deve conter: " + keyword, responseBody.contains(keyword));
        }
    }
    @And("a API deve retornar o código de retorno {int} na response da listagem")
    public void aAPIDeveRetornarOCódigoDeRetornoNaResponseDaListagem(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
    @When("eu preencho o parametro ID do livro a ser listado")
    public void euPreenchoOCampoID() {
        idLivro = "aad2afad-308a-46fb-981f-1621e65934fc";
    }

    @When("envio a requisição de listagem por ID")
    public void envioARequisiçãoGet(){
        response = listarLivroPage.listarLivrosPorID(idLivro);
    }
    @Then("a api retorna o livro de acordo com o ID pesquisado")
    public void aApiRetornaOLivroDeAcordoComOIDPesquisado() {
        String responseBody = response.getBody().asString();
        assertNotNull(responseBody);
        System.out.println("Livro retornado de acordo com o que foi enviado"+ responseBody);
        assertTrue("A resposta deve conter o parametro enviado: " , responseBody.contains(idLivro));
    }
    @When("envio a requisição de listagem")
    public void envioARequisiçãoDeListagem() {
        response = listarLivroPage.listarLivros();
    }
}

