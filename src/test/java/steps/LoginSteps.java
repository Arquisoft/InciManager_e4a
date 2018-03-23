package steps;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import asw.dbManagement.model.Agent;
import asw.inciProcessor.webService.NotifyIncidenceRESTController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
  
  private static final Logger LOG = LoggerFactory.getLogger(NotifyIncidenceRESTController.class);

  @Given("^a list of users:$")
  public void a_list_of_users(List<Agent> users) throws Throwable {
    for (Agent u: users) {
      System.out.println("Inserting user..." + u.getEmail() + " - " + u.getPassword());
    }
  }

  @When("^I login with name \"(.+)\" and password \"(.+)\"$")
  public void i_login_with_name_and_password(String name, String password) throws Throwable {
    System.out.println("Login with values..." + name + " - " + password);
  }

  @Then("^I receive a welcome message$")
  public void i_receive_a_welcome_message() throws Throwable {
    System.out.println("Wellcome value received");
  }

}
