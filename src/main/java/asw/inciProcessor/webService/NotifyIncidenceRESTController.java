package asw.inciProcessor.webService;


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
import asw.dbManagement.IncidenceService;
import asw.dbManagement.impl.GetAgentImpl;
import asw.dbManagement.model.Incidence;
import asw.inciProcessor.webService.request.PeticionNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.RespuestaNotifyIncidenceREST;
import asw.inciProcessor.webService.responses.errors.ErrorResponse;

@RestController
public class NotifyIncidenceRESTController {

	@Autowired
	private IncidenceService incidenceService;
	private KafkaProducer kafka;
	private GetAgentImpl agentService;

	
	
	
	
	
	

	
	
	//Este método hay que adecuarlo al nuevo modelo (probablemente sea necesario un nuevo constructor en incidence)
	//

	@RequestMapping(value = "/notify", method = RequestMethod.POST, headers = { "Accept=application/json",
			"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<RespuestaNotifyIncidenceREST> getPOSTpetition(@RequestBody(required = true) PeticionNotifyIncidenceREST peticion) {

		Incidence incidence = new Incidence(peticion.getLogin(),peticion.getPassword(),peticion.getName(),
				peticion.getDescription(),peticion.getLocation(), peticion.getTags());
		
		incidenceService.saveIncidence(incidence);

		kafka.sendNuevaIncidencia(incidence.getId());
		
		return new ResponseEntity<RespuestaNotifyIncidenceREST>(new RespuestaNotifyIncidenceREST(incidence), HttpStatus.OK);
		
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}
	
}

