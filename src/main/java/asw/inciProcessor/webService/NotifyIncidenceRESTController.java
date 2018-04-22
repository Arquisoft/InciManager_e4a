package asw.inciProcessor.webService;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import asw.Kafka.KafkaProducer;
import asw.dbManagement.entities.Agent;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.inciProcessor.webService.request.PeticionNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.RespuestaNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.errors.ErrorResponse;
import asw.services.AgentsService;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;

@RestController
public class NotifyIncidenceRESTController {

	@Autowired
	private IncidencesService incidenceService;
	private KafkaProducer kafka;
	
	@Autowired
	AgentsService agentService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private NotificationService notifactionService;


	@RequestMapping(value = "/notify", method = RequestMethod.POST, headers = { "Accept=application/json",
			"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<RespuestaNotifyIncidenceREST> getPOSTpetition(@RequestBody(required = true) PeticionNotifyIncidenceREST peticion) {
		
		
		if(!agentService.checkUserAndPass(peticion.getLogin(), peticion.getPassword())) {
			throw asw.factory.ErrorFactory.getError(asw.factory.ErrorFactory.Errors.INCORRECT_LOGIN);
		}
		Agent agent = agentService.getAgent(peticion.getLogin());
	
		System.out.println(peticion.getName());
		System.out.println(peticion.getDescription());
		System.out.println(peticion.getLocation());
		System.out.println(peticion.getLabels());
		System.out.println(peticion.getMoreInfo());
		System.out.println(peticion.getProperties());
		
		LatLong ll = new LatLong(peticion.getLocation().get(0),peticion.getLocation().get(1));
		
		Set<String> labels = new HashSet<String>();
		String[] parts = peticion.getLabels().split(",");
		for (int i=0; i< parts.length;i++) {
			labels.add(parts[i]);
		}
			
		
		Incidence incidence = new Incidence(agent, peticion.getName(),
				peticion.getDescription(), ll,  labels, peticion.getMoreInfo(), peticion.getProperties());
		
		Operator operator = operatorService.assignOperator();
		incidence = incidenceService.addIncidence(incidence);
		Notification notification = new Notification(); 
		notification.setIncidencia(incidence);
		notification.setDescription("Incidencia con id: " + incidence.getId());
		notification.setOperator(operator);
		
		notifactionService.addIncident(notification);
		
		
		
		//kafka.sendNuevaIncidencia(notification);
		
		return new ResponseEntity<RespuestaNotifyIncidenceREST>(new RespuestaNotifyIncidenceREST(incidence), HttpStatus.OK);
		
	}


	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}
	
}

