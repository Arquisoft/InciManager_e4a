package asw.services.chatbot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;

@Service
public class ChatbotService {
	
	private int numRes = 0;
	private List<Dupla> frases; 
	private Preguntas preguntas;	
	
	@Autowired 
	IncidencesService incidenceService; 
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	NotificationService notificationService;
	
	public ChatbotService() {
		preguntas = new Preguntas();
		init(); 
	}
	
	public void init() {
		numRes = 0;
		frases = new ArrayList<Dupla>();
		System.out.println("Chatbot inicializado");
	}
	public boolean next() {
		return numRes <= 6 ; 
	}
	public String pregunta() {		
		String pregunta = preguntas.get(numRes);
		numRes++; 
		return pregunta; 
	}
	
	public void responder(String respuesta) {
		frases.add(new Dupla(preguntas.get(numRes -1), respuesta));
	}
	
	public List<Dupla> getFrases(){
		return frases;
	}

	public Notification getSummary() {
		Incidence incidencia = new Incidence(); 
		//incidencia.setAgent(); // Nos falta el login
		incidencia.setInciName(frases.get(0).respuesta);
		incidencia.setInciDescription(frases.get(1).respuesta);
		LatLong latlong = new LatLong(frases.get(2).respuesta,frases.get(3).respuesta);
		incidencia.setLocation(latlong);
		String[] etiquetas = frases.get(4).respuesta.split(",");
		for(String etiqueta : etiquetas) {
			incidencia.addTag(etiqueta);
		}
		String[] moreInfo = frases.get(5).respuesta.split(",");
		for(String adInfo : moreInfo) {
			incidencia.addMoreInfo(adInfo);
		}
		//Añadir el hashmap chulo
		incidencia = incidenceService.addIncidence(incidencia);
		Operator operator = operatorService.assignOperator();
		Notification notification = new Notification(); 
		notification.setIncidencia(incidencia);
		notification.setDescription(incidencia.getInciDescription());
		notification.setOperator(operator);
		
		notification = notificationService.addIncident(notification);
		return notification;
	}
}
