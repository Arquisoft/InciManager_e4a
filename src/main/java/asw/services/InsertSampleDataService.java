package asw.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.dbManagement.entities.State;

@Service
public class InsertSampleDataService {
		
	
	@Autowired
	IncidencesService incidencesService;
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	NotificationService notService;
	
	@PostConstruct
	public void init() {
		
		String agente1 = "87654321B"; 
		String agente2 = "11223344C"; 
		String agente3 = "13864928C"; 
		String agente4 = "13864928D"; 
		String agente5 = "13864928E"; 
		Operator op1 = new Operator("ivan@gmail.com","ivan", "123456", 1); 
		Operator op2 = new Operator("antonio@gmail.com","antonio", "123456", 1); 
		Operator op3 = new Operator("hugo@gmail.com","hugo", "123456", 1); 
		Operator op4 = new Operator("mirza@gmail.com","mirza", "123456", 1); 
		Operator op5 = new Operator("pasadores@gmail.com","pasadores", "123456", 1);
		
		operatorService.addOperator(op1);
		operatorService.addOperator(op2);
		operatorService.addOperator(op3);
		operatorService.addOperator(op4);
		operatorService.addOperator(op5);
		
		Incidence incidencia1 = new Incidence("Prueba1", new LatLong("43.518197", "-5.641936"), agente1, "incidencia 1"); 
		incidencia1.setState(State.CANCELLED);
		Incidence incidencia2 = new Incidence("Prueba2", new LatLong("42.459789,", "-6.070053"), agente1, "incidencia 2"); 
		incidencia2.setState(State.IN_PROCESS);
		Incidence incidencia3 = new Incidence("Prueba3", new LatLong("52.459789,", "-6.070053"), agente1, "incidencia 3"); 
		incidencia3.setState(State.CLOSED);
		Incidence incidencia4 = new Incidence("Prueba4", new LatLong("42.459789,", "-10.070053"), agente2, "incidencia 4"); 
		incidencia4.setState(State.OPEN);
		Incidence incidencia5 = new Incidence("Prueba5", new LatLong("42.459789,", "-8.070053"), agente2, "incidencia 5"); 
		incidencia5.setState(State.IN_PROCESS);
		Incidence incidencia6 = new Incidence("Prueba6", new LatLong("32.459789,", "-2.070053"), agente2, "incidencia 6"); 
		incidencia6.setState(State.IN_PROCESS);
		Incidence incidencia7 = new Incidence("Prueba7", new LatLong("40.459789,", "-5.070053"), agente3, "incidencia 7"); 
		incidencia7.setState(State.CLOSED);
		Incidence incidencia8 = new Incidence("Prueba8", new LatLong("22.459789,", "-6.070053"), agente4, "incidencia 8"); 
		incidencia8.setState(State.CLOSED);
		Incidence incidencia9 = new Incidence("Prueba9", new LatLong("12.459789,", "-6.070053"), agente4, "incidencia 9"); 
		incidencia9.setState(State.IN_PROCESS);
		Incidence incidencia10 = new Incidence("Prueba10", new LatLong("12.459789,", "-6.070053"), agente3, "incidencia 10"); 
		Map<String, Object> camposExtra = new HashMap<String, Object>(); 
		camposExtra.put("hola", "que tal");
		camposExtra.put("yo bien", "y tu");
		camposExtra.put("se nos quema", "la casa");
		incidencia10.setProperties(camposExtra);
		incidencia10.addMoreInfo("Más información 1");
		incidencia10.addMoreInfo("Más información 2");
		incidencia10.addMoreInfo("El osito mola mogollón");

		incidencia10.setState(State.OPEN);
	
		incidencesService.addIncidence(incidencia1);
		incidencesService.addIncidence(incidencia2);
		incidencesService.addIncidence(incidencia3);
		incidencesService.addIncidence(incidencia4);
		incidencesService.addIncidence(incidencia5);
		incidencesService.addIncidence(incidencia6);
		incidencesService.addIncidence(incidencia7);
		incidencesService.addIncidence(incidencia8);
		incidencesService.addIncidence(incidencia9);
		incidencesService.addIncidence(incidencia10);
		
		Notification n1 = new Notification("La hemos cagao",op2,incidencia1);
		Notification n2 = new Notification("Se ha roto",op2,incidencia2);
		Notification n3 = new Notification("Pa cuenca a reparar",op2,incidencia3);
		Notification n4 = new Notification("La hemos cagao x2",op2,incidencia4);
		Notification n5 = new Notification("Notificacion de incidencia 5",op3,incidencia5);
		Notification n6 = new Notification("Notificacion de incidencia 6",op1,incidencia6);
		Notification n7 = new Notification("Notificacion de incidencia 7",op3,incidencia7);
		Notification n8 = new Notification("Notificacion de incidencia 8",op4,incidencia8);
		Notification n9 = new Notification("Notificacion de incidencia 9",op1,incidencia9);
		Notification n10 = new Notification("Notificacion de incidencia 10",op3,incidencia10);
		
		notService.addIncident(n1);
		notService.addIncident(n2);
		notService.addIncident(n3);
		notService.addIncident(n4);
		notService.addIncident(n5);
		notService.addIncident(n6);
		notService.addIncident(n7);
		notService.addIncident(n8);
		notService.addIncident(n9);
		notService.addIncident(n10);		
		
		
	}

}
