package asw.inciProcessor.webService.NotifyIncidence;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import asw.Kafka.KafkaProducer;
import asw.agent.GetAgentInfo;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.dbManagement.util.Assert;
import asw.inciProcessor.webService.request.PeticionNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.RespuestaNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.errors.ErrorResponse;
import asw.json.NotificationSerializer;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;


@RestController
public class NotifyIncidenceRESTController {

	@Autowired
	private IncidencesService incidenceService;
	
	@Autowired
	private KafkaProducer kafka;
	
	@Autowired
	GetAgentInfo agentService;
	
		
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private NotificationService notifactionService;
	


	@RequestMapping(value = "/notify", method = RequestMethod.POST, headers = { "Accept=application/json",
			"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<RespuestaNotifyIncidenceREST> getPOSTpetition(@RequestBody(required = true) PeticionNotifyIncidenceREST peticion )  throws Exception{
		

		
		AgentPO agent = agentService.checkUserAndPass(peticion.getLogin(), peticion.getPassword(), peticion.getKind());
		if(agent == null) {
			throw asw.factory.ErrorFactory.getError(asw.factory.ErrorFactory.Errors.INCORRECT_LOGIN);
		}

		String nombreIncidencia = null;
		if (!Assert.isNameIncidenceEmpty(peticion.getName()))
			nombreIncidencia = peticion.getName();

		String descripcionIncidencia = null;
		if (!Assert.isDescriptionIncidenceEmpty(peticion.getDescription()))
			descripcionIncidencia = peticion.getDescription();
		
		LatLong ll = null;
		if (!Assert.isLocationIncidenceInvalid(peticion.getLocation()))
			ll = new LatLong(peticion.getLocation().get(0),peticion.getLocation().get(1));
		
		Set<String> labels = new HashSet<String>();
		if (!Assert.isLabelsIncidenceInvalid(peticion.getLabels())) {
			String[] parts = peticion.getLabels().split(",");
			for (int i=0; i< parts.length;i++) {
				labels.add(parts[i]);
			}
		}	
		
		List<String> aditionalInfo = null;
		if (!Assert.isMoreInfoIncidenceInvalid(peticion.getMoreInfo())) {
			aditionalInfo = peticion.getMoreInfo();
		}
		
		Map<String, Object> properties = null;
		if (!Assert.isPropertiesInvalid(peticion.getProperties()))
			properties = peticion.getProperties();

		
		Incidence incidence = new Incidence(agent.id, nombreIncidencia,
				descripcionIncidencia, ll,  labels, aditionalInfo, properties);
		
		Operator operator = operatorService.assignOperator();
		incidence = incidenceService.addIncidence(incidence);
		Notification notification = new Notification(); 
		notification.setIncidencia(incidence);
		notification.setDescription("Incidencia con id: " + incidence.getId());
		notification.setOperator(operator);
		
		notification = notifactionService.addIncident(notification);
		
		
		
		
		
		kafka.sendNuevaNotificacion(notification);
		
		return new ResponseEntity<RespuestaNotifyIncidenceREST>(new RespuestaNotifyIncidenceREST(incidence, operator.getEmail()), HttpStatus.OK);
		
	}


	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}
	
}

