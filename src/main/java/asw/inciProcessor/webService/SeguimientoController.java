package asw.inciProcessor.webService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.services.AgentsService;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;
import asw.services.chatbot.ChatbotService;

@Controller
public class SeguimientoController {
	
	@Autowired
	ChatbotService chatbot; 
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	AgentsService agentService;
	
	@Autowired
	IncidencesService incidenceService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping("/")
	public String inicalicerLogin(Model model) {
		return "seguimientoIncidencias";
	}
	
	@RequestMapping("/chatbot")
	public String chatBot(Model model) {		
		chatbot.init();
		model.addAttribute("pregunta", chatbot.pregunta());
		return "chatbot";
	}
	
	
	@RequestMapping("/responderChatbot")
	public String respuestaChatbot(@RequestParam(required = true) String respuesta, Model model) {
		chatbot.responder(respuesta);
		if(chatbot.next()) {
			model.addAttribute("pregunta", chatbot.pregunta());
			model.addAttribute("duplas", chatbot.getFrases());
			return "chatbot";
		}
		else { 
			model.addAttribute("info", chatbot.getSummary());
			return "incidenciaCreada";
		}
	}
	
	@RequestMapping("/magia")
	public String magiaNegra(Model model) {
		Incidence incidencia1 = new Incidence("Prueba1", new LatLong("43.518197", "-5.641936"));
		Operator operator = operatorService.assignOperator();
		incidencia1 = incidenceService.addIncidence(incidencia1);
		Notification notification = new Notification(); 
		notification.setIncidencia(incidencia1);
		notification.setDescription("Incidencia con id: " + incidencia1.getId());
		notification.setOperator(operator);
		
		notification = notificationService.addIncident(notification);
		model.addAttribute("info", notification);
		return "incidenciaCreada";
	}
}