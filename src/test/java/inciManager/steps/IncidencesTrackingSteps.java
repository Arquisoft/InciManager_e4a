package inciManager.steps;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.Application;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;
import asw.services.AgentsService;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class IncidencesTrackingSteps {
	
	@Autowired
	AgentsService agentservice;
	@Autowired
	IncidencesService incidenceservice;
	@Autowired
	NotificationService notificationservice;
	
	private String agent;
	
	@Given("^a registered agent with login \"([^\"]+)\" password \"([^\"]+)\" and kind \"([^\"]+)\"")
	public void a_registered_agent_with_login_password_and_kind(String login,String password,String kind) throws Throwable {
		agentservice.checkUserAndPass(login, password, kind);
		agent = login;
	}
	
	@Then("^he will be able to track the \"([^\"]+)\" the \"([^\"]+)\" and the \"([^\"]+)\" of an incidence")
	public void he_will_be_able_to_tack_the_name_the_state_and_the_operator_of_an_incidence(String name, String state, long operator) throws Throwable {
		List<Incidence> incidences = incidenceservice.getIncidences();
		List<Notification> notifications = notificationservice.getNotifications();
		for (int i=0;i<incidences.size();i++) {
			if(incidences.get(i).getAgent() == agent) {
				for(int j=0;j<notifications.size();j++) {
					if(notifications.get(j).getIncidencia()==incidences.get(i))
						assertTrue(incidences.get(i).getInciName().equals(name) && incidences.get(i).getState().name().equals(state) && (notifications.get(j).getOperator().getID() == operator));
						
				}		
			}
				
		}
	}
}
