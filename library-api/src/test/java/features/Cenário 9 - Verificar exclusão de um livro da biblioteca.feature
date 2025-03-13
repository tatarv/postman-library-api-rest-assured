@exclusão
Feature: Exclusão de livros
  Scenario: Cenário 9 - Verificar exclusão de um livro da biblioteca
    Given que eu quero excluir um livro cadastrado na biblioteca
    And eu preencho o parametro ID do livro a ser excluído
    When eu envio a requisição de exclusão
    Then o livro é excluído
    And a API deve retornar o código de retorno 204 na response da exclusão