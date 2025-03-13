@exclusão
Feature: Exclusão de livros
  Scenario: Cenário 10 - Verificar falha na exclusão de um livro que faz parte da coleção permanente
    Given que eu quero excluir um livro cadastrado na biblioteca
    And eu preencho o parametro ID de um livro que faz parte da coleção permanente para exclusão
    When eu envio a requisição de exclusão de um livro que faz parte da coleção permanente
    Then o livro é excluído
    And a API deve retornar o código de retorno 403 na response da exclusão
    And a API deve retornar a mensagem na response da exclusão: "You cannot modify books in the permanent collection! Book with id '53a37668-c99f-46f9-af00-c582924f422b' is in the permanent collection."