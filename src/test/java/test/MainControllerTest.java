package test;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import asw.Application;
import asw.dbManagement.entities.AgentPO;
import asw.services.AgentsService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;
  
  private MockHttpSession session;
  
  private AgentsService agentService;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    session = new MockHttpSession();
  }

  /*@Test
  public void testLanding() throws Exception {
    mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("Home")));
  }

  @Test
  public void testSort() throws Exception {
    mvc.perform(get("/sort")).andExpect(status().isOk()).andExpect(content().string(containsString("sorted")));
  }

  @Test
  public void testSearchOK() throws Exception {
    mvc.perform(get("/search?name=pepe")).andExpect(status().isOk()).andExpect(content().string(containsString("pepe")));
  }*/
  
  @Test
	public void Test01ChatbotRedirectToLogin() throws Exception {
		MockHttpServletRequestBuilder request = get("/chatbot").session(session);
		mvc.perform(request).andExpect(view().name("login"));
	}
	
	@Test
	public void Test02Chatbot() throws Exception {
		session.setAttribute("agent", new AgentPO());
		MockHttpServletRequestBuilder request = get("/chatbot").session(session);
		mvc.perform(request).andExpect(view().name("chatbot"));
	}
	
  
}
