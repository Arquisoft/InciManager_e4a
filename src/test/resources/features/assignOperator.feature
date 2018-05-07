Feature: All incidences created are automatically assigned to an operator
Scenario: 
    Given a recently created incidence 
    When it is added to the database
    Then the incidence should have an assigned operator
    