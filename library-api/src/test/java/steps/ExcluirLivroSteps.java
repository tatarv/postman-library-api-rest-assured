package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.ExcluirLivroPage;
import pages.ListarLivroPage;
import static org.junit.Assert.*;

public class ExcluirLivroSteps {
    private ExcluirLivroPage excluirLivroPage;
    private ListarLivroPage listarLivroPage;
    private Response response;
    private String idLivro;
    private String idLivroPermanente;

    @Given("que eu quero excluir um livro cadastrado na biblioteca")
    public void queEuQueroExcluirUmLivroCadastradoNaBiblioteca() {
        excluirLivroPage = new ExcluirLivroPage();
    }
    @And("eu preencho o parametro ID do livro a ser excluído")
    public void euPreenchoOParametroIDDoLivroASerExcluído() {
        idLivro = "253ebf5d-0168-42e5-a013-a70b73bfe7b6";
    }
    @When("eu envio a requisição de exclusão")
    public void euEnvioARequisiçãoDeExclusão() {
        response = excluirLivroPage.deletarLivroPorID(idLivro);
    }
    @Then("o livro é excluído")
    public void oLivroÉExcluído() {
        listarLivroPage = new ListarLivroPage();
        int statusCode = response.getStatusCode();

        if (statusCode == 204) {
            System.out.println("Livro excluído com sucesso.\nCódigo de retorno: " + statusCode);
            Response verifyResponse = listarLivroPage.listarLivrosPorID(idLivro);

            assertEquals("O livro deve ser inexistente e retornar 404", 404, verifyResponse.getStatusCode());
            System.out.println("Confirmação de que o livro foi excluído: " + verifyResponse.getBody().asString());
        } else {
            System.err.println("Erro ao excluir o livro. Código de retorno inesperado: " + statusCode);
            throw new AssertionError("Erro ao excluir o livro. Código de retorno: " + statusCode);
        }

    }
    @And("a API deve retornar o código de retorno {int} na response da exclusão")
    public void aAPIDeveRetornarOCódigoDeRetornoNaResponseDaExclusão(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("eu preencho o parametro ID de um livro que faz parte da coleção permanente para exclusão")
    public void euPreenchoOParametroIDDeUmLivroQueFazParteDaColeçãoPermanenteParaExclusão() {
        idLivroPermanente = "53a37668-c99f-46f9-af00-c582924f422b";
    }

    @When("eu envio a requisição de exclusão de um livro que faz parte da coleção permanente")
    public void euEnvioARequisiçãoDeExclusãoDeUmLivroQueFazParteDaColeçãoPermanente() {
        response = excluirLivroPage.deletarLivroPorID(idLivroPermanente);

    }

    @And("a API deve retornar a mensagem na response da exclusão: {string}")
    public void aAPIDeveRetornarAMensagemNaResponseDaExclusão(String mensagemEsperada) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(mensagemEsperada));
    }
}
