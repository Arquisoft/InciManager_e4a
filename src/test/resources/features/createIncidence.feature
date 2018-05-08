Feature: Authenticated agents can add incidences
Scenario: 
    Given an agent with login "13864928P" password "123456" and kind "Person"
    When he creates an incidence with name "incidenciaExtraordinaria" description "descripcionIncreible"
    Then the DB will have that incidence
    