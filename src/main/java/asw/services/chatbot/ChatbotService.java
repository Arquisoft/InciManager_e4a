package asw.services.chatbot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.Kafka.KafkaProducer;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.dbManagement.entities.State;
import asw.dbManagement.util.Assert;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;

@Service
public class ChatbotService {
	
	private int numRes = 0;
	private List<Dupla> frases; 
	private Preguntas preguntas;	
	private AgentPO agent;
	@Autowired 
	IncidencesService incidenceService; 
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	private KafkaProducer kafka;
	
	@Autowired
	NotificationService notificationService;
	
	public ChatbotService() {
		preguntas = new Preguntas();
		init(null); 
	}
	
	public void init(AgentPO agente) {
		numRes = 0;
		frases = new ArrayList<Dupla>();
		this.agent = agente;
		System.out.println("Chatbot inicializado");
	}
	public boolean next() {
		return numRes <= 6 ; 
	}
	public String[] pregunta() {		
		String pregunta[] = new String[2];
		pregunta[0] = preguntas.get(numRes);		
		pregunta[1] = preguntas.getAclaracion(numRes);
		numRes++; 
		return pregunta; 
	}
	
	public boolean responder(String respuesta) {
		boolean correct=false;
		if(numRes-1==2) {
			correct = Assert.locationIncidenceChatbotCorrect(respuesta) &&
					Assert.responseChatbotEmpty(respuesta);
		}
		else if(numRes-1==3) {
			correct = Assert.locationIncidenceChatbotCorrect(respuesta) &&
					Assert.responseChatbotEmpty(respuesta);
		}
		else if(numRes-1==4) {		
			correct = Assert.labelsIncidenceChatbotCorrect(respuesta) && 
					Assert.responseChatbotEmpty(respuesta);
		}
		else if(numRes-1==5){
			correct = Assert.labelsIncidenceChatbotCorrect(respuesta) && 
					Assert.responseChatbotEmpty(respuesta);
		}
		else if(numRes-1==6){
			correct = Assert.propertiesIncidenceChatbotCorrect(respuesta) && 
					Assert.responseChatbotEmpty(respuesta);
		}
		else {		
			correct=Assert.responseChatbotEmpty(respuesta);
		}
		if(correct) {
			frases.add(new Dupla(preguntas.get(numRes - 1), respuesta));
			return true;
		}
		return false;	
	}
	
	public List<Dupla> getFrases(){
		return frases;
	}

	public Notification getSummary() {
		Incidence incidencia = new Incidence(); 
		incidencia.setAgent(agent.id);
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
		incidencia.setState(State.OPEN);
		String[] properties = frases.get(6).respuesta.split(",");
		for(int i=0;i<properties.length;i++) {
			String[] properties2 = properties[i].split(":");
			incidencia.addProperty(properties2[0], properties2[1]);
		}
		incidencia = incidenceService.addIncidence(incidencia);
		Operator operator = operatorService.assignOperator();
		Notification notification = new Notification(); 
		notification.setIncidencia(incidencia);
		notification.setDescription(incidencia.getInciDescription());
		notification.setOperator(operator);
		
		notification = notificationService.addIncident(notification);
		
		kafka.sendNuevaNotificacion(notification);
		return notification;
	}
}
