package asw.inciProcessor.webService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbManagement.entities.Agent;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.util.Assert;
import asw.services.AgentsService;


@Controller
public class HomeController {
	
	@Autowired
	AgentsService agentService;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLogin(HttpSession session, @RequestParam String login, @RequestParam String password, @RequestParam String kind,
			Model model) {
		Assert.isLoginEmpty(login);
		Assert.isPasswordEmpty(password);
		Assert.isKindEmpty(kind);

		AgentPO agente = agentService.checkUserAndPass(login, password, kind);
		

		session.setAttribute("agent", agente);
		
		return "index";
	}
}
