package asw.inciProcessor.webService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbManagement.entities.AgentPO;
import asw.services.chatbot.ChatbotService;

@Controller
public class ChatBotController {
	
	@Autowired
	ChatbotService chatbot; 
	
	@RequestMapping("/chatbot")
	public String chatBot(HttpSession session, Model model) {		
		AgentPO agente = (AgentPO) session.getAttribute("agent");
		if(agente == null) {
			return "login";
		}
		chatbot.init(agente);
		model.addAttribute("pregunta", chatbot.pregunta());
		return "chatbot";
	}
	
	
	@RequestMapping("/responderChatbot")
	public String respuestaChatbot(HttpSession session, @RequestParam String respuesta, Model model) {
		AgentPO agente = (AgentPO) session.getAttribute("agent");
		if(agente == null) {
			return "login";
		}
		if(!chatbot.responder(respuesta)) {
			return "redirect:/chatbot?error";
		}
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
}
