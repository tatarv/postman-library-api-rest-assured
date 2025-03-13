@cadastro
Feature: Cadastro de livros
  Scenario: Cenário 3 - Verificar falha no cadastro de livro com valores excedentes nos campos obrigatórios
    Given que eu quero cadastrar um livro na biblioteca
    And preencho os campos obrigatórios informando valores que ultrapassam o tamanho permitido dos campos
    When envio a requisição de cadastro
    Then And o livro não deve ser salvo na biblioteca
    And a API deve retornar o código de retorno 500 na response do cadastro
    And a API deve retornar a mensagem na response do cadastro: "value too long for type character varying(255)"
