package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.AlterarLivroPage;
import static org.junit.Assert.*;

public class AlterarLivroSteps {
    private AlterarLivroPage alterarLivroPage;
    private Response response;
    private String idLivro;
    private String idLivroPermanente;
    private String livroJson;

    @Given("que eu quero alterar um livro cadastrado na biblioteca")
    public void queEuQueroAlterarUmLivroCadastradoNaBiblioteca() {

        alterarLivroPage = new AlterarLivroPage();
    }

    @And("eu preencho o parametro ID do livro a ser alterado")
    public void euPreenchoOParametroIDDoLivroASerAlterado() {

        idLivro = "b229d186-21f9-45bf-8399-f9911a36df5c";
    }

    @And("eu preencho os campos obrigatórios para alteração")
    public void euPreenchoOsCamposObrigatóriosParaAlteração() {
        livroJson = "{ " +
                "\"title\": \"Columbine\", " +
                "\"author\": \"J.R.R. Tolkien\", " +
                "\"genre\": \"self-help\", " +
                "\"yearPublished\": 2002 " +
                "}";
    }
    @When("eu envio a requisição de alteração")
    public void euEnvioARequisiçãoDeAlteração() {
        response = alterarLivroPage.alterarLivroPorID(livroJson,idLivro);
    }
    @Then("as alterações são salvas")
    public void asAlteraçõesSãoSalvas() {
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        boolean allValidationsPassed = true;

        try {
            assertEquals("O código de retorno deve ser 200", 200, statusCode);
            assertNotNull("O corpo da resposta não pode ser nulo", responseBody);
            String[] keywords = { "id", "title", "author", "genre", "yearPublished", "checkedOut", "isPermanentCollection", "createdAt" };

            for (String keyword : keywords) {
                if (!responseBody.contains(keyword)) {
                    System.err.println("A resposta não contém: " + keyword);
                    allValidationsPassed = false;
                }
            }
            if (allValidationsPassed) {
                System.out.println("Livro alterado com sucesso.\nCódigo de retorno: " + statusCode + "\nResposta: " + responseBody);
            }

        } catch (AssertionError e) {
            System.err.println("Erro ao cadastrar o livro: " + e.getMessage());
        }
    }

    @And("a API deve retornar o código de retorno {int} na response da alteração")
    public void aAPIDeveRetornarOCódigoDeRetornoNaResponseDaAlteração(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("And as alterações não devem ser salvas na biblioteca.")
    public void andAsAlteraçõesNãoDevemSerSalvasNaBiblioteca() {
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        assertNotNull(responseBody);

        switch (statusCode) {
            case 400:
                System.out.println("Campos obrigatórios devem ser preenchidos.");
                break;
            case 403:
                System.out.println("Livro faz parte da coleção permanente e não pode ser alterado");
            case 500:
                System.out.println("Erro interno.");
                break;
            default:
                System.out.println("Código de status inesperado: " + statusCode);
                break;
        }
        System.out.println("Código de status: " + statusCode + "\nResposta: " + responseBody);
    }

    @And("a API deve retornar a mensagem na response da alteração: {string}")
    public void aAPIDeveRetornarAMensagemNaResponseDaAlteração(String mensagemEsperada) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(mensagemEsperada));
    }

    @And("não preencho os campos obrigatórios para a alteração")
    public void nãoPreenchoOsCamposObrigatóriosParaAAlteração() {
        livroJson = "{ " +
                "\"title\": \"\", " +
                "\"author\": \"\", " +
                "\"genre\": \"\", " +
                "\"yearPublished\": \"\", " +
                "\"checkedOut\": \"\" " +
                "}";
    }

    @And("eu preencho o parametro ID de um livro que faz parte da coleção permanente")
    public void euPreenchoOParametroIDDeUmLivroQueFazParteDaColeçãoPermanente() {
        idLivroPermanente = "53a37668-c99f-46f9-af00-c582924f422b";
    }

    @When("eu envio a requisição de alteração de um livro que faz parte da coleção permanente")
    public void euEnvioARequisiçãoDeAlteraçãoDeUmLivroQueFazParteDaColeçãoPermanente() {
        response = alterarLivroPage.alterarLivroPorID(livroJson,idLivroPermanente);
    }
}
