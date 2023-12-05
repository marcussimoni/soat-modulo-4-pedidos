Feature: Listar produtos

  Scenario: Listar produtos cadastrados
    Given Cliente deseja visualizar os produtos disponíveis
    When Cliente acessa a lista de produtos
    Then Apresentar todos os produtos cadastrados e disponíveis
