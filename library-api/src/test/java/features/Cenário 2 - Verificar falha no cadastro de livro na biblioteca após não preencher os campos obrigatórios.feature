@cadastro
Feature: Cadastro de livros
  Scenario: Cenário 2 - Verificar falha no cadastro de livro na biblioteca após não preencher os campos obrigatórios
    Given que eu quero cadastrar um livro na biblioteca
    And não preencho os campos obrigatórios
    When envio a requisição de cadastro
    Then And o livro não deve ser salvo na biblioteca
    And a API deve retornar o código de retorno 400 na response do cadastro
    And a API deve retornar a mensagem na response do cadastro: "body/title must NOT have fewer than 1 characters, body/author must NOT have fewer than 1 characters, body/genre must NOT have fewer than 1 characters, body/yearPublished must be integer"
