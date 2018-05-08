Feature: Agents can track the incidences they've created
Scenario Outline: 
    Given a registered agent with login <login> password <password> and kind <kind>
    Then he will be able to track the name <name> of an incidence
    Examples:
      | login   	 		| password       	| kind			| name		  	| state			| operator 	|
      | 87654321B	 		| 123456     	 	| Person		| Prueba2	 	| IN_PROCESS	| 2		  	|
      | 87654321B	 		| 123456     	 	| Person		| Prueba3	 	| CLOSED		| 2		  	|
      | 11223344C	 		| 123456     		| Person		| Prueba4		| OPEN			| 2		  	|
      | 11223344C	 		| 123456     		| Person		| Prueba5		| IN_PROCESS	| 3		  	|
      | 11223344C	 		| 123456     		| Person		| Prueba6		| IN_PROCESS	| 1		  	|
      | 11223344C	 		| 123456     		| Person		| Prueba7		| CLOSED		| 3		  	|