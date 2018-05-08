package asw.inciProcessor.webService.NotifyIncidence;

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
			session.setAttribute("direccion", "chatbot");
			return "login";			
		}
		chatbot.init(agente);
		model.addAttribute("pregunta", chatbot.pregunta()[0]);
		return "chatbot";
	}
	
	
	@RequestMapping("/responderChatbot")
	public String respuestaChatbot(HttpSession session, @RequestParam String respuesta, Model model) {
		AgentPO agente = (AgentPO) session.getAttribute("agent");
		if(agente == null) {
			session.setAttribute("direccion", "responderChatbot");
			return "login";
		}
		if(!chatbot.responder(respuesta)) {
			return "redirect:/chatbot?error";
		}
		if(chatbot.next()) {
			String[] pregunta = chatbot.pregunta();			
			model.addAttribute("pregunta", pregunta[0]);
			model.addAttribute("aclaracion", pregunta[1]);
			model.addAttribute("duplas", chatbot.getFrases());
			return "chatbot";
		}
		else { 
			model.addAttribute("info", chatbot.getSummary());
			return "incidenciaCreada";
		}
	}
}
