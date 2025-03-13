@listagem
Feature: Listagem de livros
  Scenario: Cenário 5 - Verificar listagem de livros por ID
    Given que eu quero listar os livros que estão cadastrados na biblioteca
    When eu preencho o parametro ID do livro a ser listado
    And envio a requisição de listagem por ID
    Then a api retorna o livro de acordo com o ID pesquisado
    And a API deve retornar o código de retorno 200 na response da listagem