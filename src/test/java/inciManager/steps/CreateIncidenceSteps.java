package inciManager.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.Application;
import asw.agent.GetAgentInfo;
import asw.dbManagement.entities.Incidence;
import asw.services.IncidencesService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class CreateIncidenceSteps {
	
	@Autowired
	GetAgentInfo agentservice;
	@Autowired
	IncidencesService incidenceservice;
	
	private Incidence incidence;
	private String agent;
	
	@Given("^an agent with login \"([^\"]+)\" password \"([^\"]+)\" and kind \"([^\"]+)\"")
	public void an_agent_with_login(String login,String password,String kind) throws Throwable {
		agentservice.checkUserAndPass(login, password, kind);
		agent = login;
	}

	@When("^he creates an incidence with name \"([^\"]+)\" description \"([^\"]+)\"")
	public void he_creates_an_incidence(String name, String description) throws Throwable {
		incidence = new Incidence(agent, name, description);
		incidenceservice.addIncidence(incidence);
	}
	
	@Then("^the DB will have that incidence")
	public void the_db_will_have_that_incidence_with_the_attributes() throws Throwable {
		List<Incidence> incidences = incidenceservice.getIncidences();
		for (int i=0;i<incidences.size();i++) {
			if(incidences.get(i) == incidence)
				assertTrue(incidences.get(i).getInciName().equals(incidence.getInciName()) && incidences.get(i).getInciDescription().equals(incidence.getInciDescription()));
		}
	}
}
