@alteração
Feature: Listagem de livros
  Scenario: Cenário 6 - Verificar alteração de livros por ID
    Given que eu quero alterar um livro cadastrado na biblioteca
    And eu preencho o parametro ID do livro a ser alterado
    And eu preencho os campos obrigatórios para alteração
    When eu envio a requisição de alteração
    Then as alterações são salvas
    And a API deve retornar o código de retorno 200 na response da alteração