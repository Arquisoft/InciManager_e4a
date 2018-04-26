package asw.inciProcessor.webService;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.services.AgentsService;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;

@Controller
public class SeguimientoController {
	
	
	
	@Autowired
	OperatorService operatorService;
	
	@Autowired
	AgentsService agentService;
	
	@Autowired
	IncidencesService incidenceService;
	
	@Autowired
	NotificationService notificationService;
			
	@RequestMapping("/seguimientoIncidencias")
	public String seguimientoIncidencias(Model model) {
		List<Notification> notificaciones = notificationService.getNotifications();
		if(notificaciones == null) {
			System.out.println("KE PAZÓ");
			return "index";
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