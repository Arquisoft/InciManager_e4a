#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Gestión de usuario
  I want to use this template for my feature file

  @tag1
  Scenario: Entrar en el sistema
 			Entrar en el sistema con un nombre de usuario existente
    Given una lista de usuarios:
    	| name  | value 		 | status  |
      | pepe  |  123456    | success |
      | luis	|  12345	 	 | Fail    |
    When I login with name "luis" and password "123456"
    Then I receive a "success" message