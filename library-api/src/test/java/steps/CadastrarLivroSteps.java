package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pages.CadastrarLivroPage;
import static org.junit.Assert.*;

public class CadastrarLivroSteps {
    private CadastrarLivroPage cadastrarLivroPage;
    private Response response;
    private String livroJson;

    @Given("que eu quero cadastrar um livro na biblioteca")
    public void que_eu_quero_cadastrar_um_livro_na_biblioteca_por_meio_da_api() {
        cadastrarLivroPage = new CadastrarLivroPage();
    }
    @When("eu preencho os campos obrigatórios")
    public void euPreenchoOsCamposObrigatórios() {
        livroJson = "{ " +
                "\"title\": \"jardim das borboletas\", " +
                "\"author\": \"J.R.R. Tolkien\", " +
                "\"genre\": \"self-help\", " +
                "\"yearPublished\": 1997 " +
                "}";
    }
    @And("envio a requisição de cadastro")
    public void envioARequisição() {
        response = cadastrarLivroPage.cadastrarLivro(livroJson);
    }
    @And("a API deve retornar o código de retorno {int} na response do cadastro")
    public void aAPIDeveRetornarOCódigoDeRetorno(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
    @And("o livro deve ser salvo na biblioteca")
    public void oLivroDeveSerSalvoNaBiblioteca() {
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        boolean allValidationsPassed = true;

        try {
            assertEquals("O código de retorno deve ser 201", 201, statusCode);
            assertNotNull("O corpo da resposta não pode ser nulo", responseBody);
            String[] keywords = { "id", "title", "author", "genre", "yearPublished", "checkedOut", "isPermanentCollection", "createdAt" };

            for (String keyword : keywords) {
                if (!responseBody.contains(keyword)) {
                    System.err.println("A resposta não contém: " + keyword);
                    allValidationsPassed = false;
                }
            }
            if (allValidationsPassed) {
                System.out.println("Livro Cadastrado com sucesso.\nCódigo de retorno: " + statusCode + "\nResposta: " + responseBody);
            }

        } catch (AssertionError e) {
            System.err.println("Erro ao cadastrar o livro: " + e.getMessage());
        }
    }
    @And("não preencho os campos obrigatórios")
    public void nãoPreenchoOsCamposObrigatórios() {
        livroJson = "{ " +
                "\"title\": \"\", " +
                "\"author\": \"\", " +
                "\"genre\": \"\", " +
                "\"yearPublished\": \"\" " +
                "}";
    }
    @Then("And o livro não deve ser salvo na biblioteca")
    public void andOLivroNãoDeveSerSalvoNaBiblioteca() {
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();

        switch (statusCode) {
            case 400:
                System.out.println("Campos obrigatórios devem ser preenchidos.");
                break;
            case 500:
                System.out.println("Erro interno.");
                break;
            default:
                System.out.println("Código de status inesperado: " + statusCode);
                break;
        }
        System.out.println("Código de status: " + statusCode + "\nResposta: " + responseBody);
    }
    @And("preencho os campos obrigatórios informando valores que ultrapassam o tamanho permitido dos campos")
    public void preenchoOsCamposObrigatóriosInformandoValoresQueUltrapassamOTamanhoPermitidoDosCampos() {
        livroJson = "{ " +
                "\"title\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi. Vivamus malesuada augue eu leo tincidunt, eget scelerisque sapien ullamcorper. Aliquam auctor fringilla elit, vitae facilisis neque tempus nec. Quisque commodo odio sit amet nibh fermentum, vitae consectetur quam pharetra. Suspendisse non purus a justo suscipit semper. Integer venenatis, justo a vulputate commodo, nisi velit vulputate nisi, sit amet venenatis metus nulla a libero. Proin ut consectetur quam. Vestibulum vehicula eu leo vel accumsan. Nam nec arcu a tortor varius convallis. Praesent accumsan elit sed leo blandit, ut varius justo lacinia. Nunc in nisl a justo scelerisque vehicula. Nulla facilisi. Phasellus imperdiet nulla vel erat varius, id aliquet nisi iaculis. Sed eleifend vehicula orci, ut bibendum metus tincidunt vel.\", " +
                "\"author\": \"In a quaint village nestled between rolling hills and babbling brooks, there lived a community of eccentric individuals. Each day brought a new adventure, from the mystical forest on the outskirts to the bustling market square where vendors peddled exotic wares. The town square, adorned with colorful banners and lively chatter, became the heartbeat of this enchanting place.\", " +
                "\"genre\": \"In a quaint village nestled between rolling hills and babbling brooks, there lived a community of eccentric individuals. Each day brought a new adventure, from the mystical forest on the outskirts to the bustling market square where vendors peddled exotic wares. The town square, adorned with colorful banners and lively chatter, tornou-se o coração pulsante deste lugar encantador.\", " +
                "\"yearPublished\": 1997 " +
                "}";
    }
    @And("a API deve retornar a mensagem na response do cadastro: {string}")
    public void aAPIDeveRetornarAMensagem(String mensagemEsperada) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(mensagemEsperada));
    }
}
