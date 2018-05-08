package inciManager.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.Application;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;
import asw.dbManagement.entities.State;
import asw.services.IncidencesService;
import asw.services.NotificationService;
import asw.services.OperatorService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class AssignOperatorSteps {
	
	@Autowired
	OperatorService operatorservice;
	@Autowired
	IncidencesService incidenceservice;
	@Autowired
	NotificationService notificationservice;
	
	private Incidence incidence;
	private Operator operator;
	private Notification notification;
	
	@Given("^a recently created incidence")
	public void a_recently_created_incidence() throws Throwable {
		incidence = new Incidence("InciPrueba", new LatLong("42.459789,", "-10.070053"), "13864928E", "DescripPrueba"); 
		incidence.setState(State.OPEN);
	}
	@Then("^it is added to the database")
	public void it_is_added_to_the_database() throws Throwable {
		operator = operatorservice.assignOperator();
		incidence = incidenceservice.addIncidence(incidence);
		notification = new Notification(); 
		notification.setIncidencia(incidence);
		notification.setDescription("Incidencia con id: " + incidence.getId());
		notification.setOperator(operator);
		
		notification = notificationservice.addIncident(notification);
	}
	
	@Then("^the incidence should have an assigned operator")
	public void the_incidence_should_have_an_assigned_operator() throws Throwable {
		notification.getOperator();
		assertTrue(notification.getOperator() == operator);
	}
}
