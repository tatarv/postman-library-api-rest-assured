@listagem
Feature: Listagem de livros
  Scenario: Cenário 4 - Verificar listagem de todos os livros da biblioteca
    Given que eu quero listar os livros que estão cadastrados na biblioteca
    When envio a requisição de listagem
    Then todos os livros que estão cadastrados devem ser retornados.
    And a API deve retornar o código de retorno 200 na response da listagem
