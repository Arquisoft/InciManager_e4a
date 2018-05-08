package asw.inciProcessor.webService.FollowIncidence;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import asw.agent.GetAgentInfo;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;

@Controller
public class SeguimientoController {
	
	
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	GetAgentInfo agentService;
	
	@Autowired
	IncidencesService incidenceService;
	
	@Autowired
	NotificationService notificationService;
			
	@RequestMapping("/seguimientoIncidencias")
	public String seguimientoIncidencias(HttpSession session, Model model) {
		AgentPO agente = (AgentPO) session.getAttribute("agent");
		
		if(agente == null) {
			session.setAttribute("direccion", "seguimientoIncidencias");
			System.out.println(session.getAttribute("direccion"));
			return "login";			
		}
		List<Notification> notificaciones = notificationService.getNotifications(agente.id);
		if(notificaciones == null) {			
			return "seguimientoIncidencias";
		}
		model.addAttribute( "notifications" , notificaciones); 
		return "seguimientoIncidencias";
	}
	
	
	
	@RequestMapping("/magia") //TODO DELETE
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