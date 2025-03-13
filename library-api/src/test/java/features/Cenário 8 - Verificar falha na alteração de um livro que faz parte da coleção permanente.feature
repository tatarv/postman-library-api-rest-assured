@alteração
Feature: Alteração de livros
  Scenario: Cenário 8 - Verificar falha na alteração de um livro que faz parte da coleção permanente
    Given que eu quero alterar um livro cadastrado na biblioteca
    And eu preencho o parametro ID de um livro que faz parte da coleção permanente
    And eu preencho os campos obrigatórios para alteração
    When eu envio a requisição de alteração de um livro que faz parte da coleção permanente
    Then And as alterações não devem ser salvas na biblioteca.
    And a API deve retornar o código de retorno 403 na response da alteração
    And a API deve retornar a mensagem na response da alteração: "You cannot modify books in the permanent collection! Book with id '53a37668-c99f-46f9-af00-c582924f422b' is in the permanent collection."
