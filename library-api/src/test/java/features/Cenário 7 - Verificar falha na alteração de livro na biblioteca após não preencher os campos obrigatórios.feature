@alteração
Feature: Alteração de livros
  Scenario: Cenário 7 - Verificar falha na alteração de livro na biblioteca após não preencher os campos obrigatórios
    Given que eu quero alterar um livro cadastrado na biblioteca
    And eu preencho o parametro ID do livro a ser alterado
    And não preencho os campos obrigatórios para a alteração
    When eu envio a requisição de alteração
    Then And as alterações não devem ser salvas na biblioteca.
    And a API deve retornar o código de retorno 400 na response da alteração
    And a API deve retornar a mensagem na response da alteração: "body/title must NOT have fewer than 1 characters, body/author must NOT have fewer than 1 characters, body/genre must NOT have fewer than 1 characters, body/yearPublished must be integer, body/checkedOut must be boolean"
