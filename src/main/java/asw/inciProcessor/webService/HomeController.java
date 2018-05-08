package asw.inciProcessor.webService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import asw.agent.GetAgentInfo;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.util.Assert;


@Controller
public class HomeController {
	
	@Autowired
	GetAgentInfo agentService;
	
	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		session.setAttribute("direccion", "");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		session.setAttribute("direccion", "");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLogin(HttpSession session, @RequestParam String login, @RequestParam String password, @RequestParam String kind,
			Model model) {
		Assert.isLoginEmpty(login);
		Assert.isPasswordEmpty(password);
		Assert.isKindEmpty(kind);		
		
		AgentPO agente = agentService.checkUserAndPass(login, password, kind);
		if(agente == null) {
			return "redirect:/login?error";
		}
		session.setAttribute("agent", agente);		
		String direccion = (String)session.getAttribute("direccion");
		if( direccion != "") {
			System.out.println("Direccion: " + direccion);
			return "redirect:/" + direccion;
		}
		return "index";
	}
}
