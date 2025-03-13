@cadastro
Feature: Cadastro de livros
  Scenario: Cenário 1 - Verificar cadastro de livro na biblioteca
    Given que eu quero cadastrar um livro na biblioteca
    When eu preencho os campos obrigatórios
    And envio a requisição de cadastro
    Then o livro deve ser salvo na biblioteca
    And a API deve retornar o código de retorno 201 na response do cadastro