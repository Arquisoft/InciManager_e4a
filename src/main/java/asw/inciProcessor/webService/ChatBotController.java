package asw.inciProcessor.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.services.chatbot.ChatbotService;

@Controller
public class ChatBotController {
	
	@Autowired
	ChatbotService chatbot; 
	
	@RequestMapping("/chatbot")
	public String chatBot(Model model) {		
		chatbot.init();
		model.addAttribute("pregunta", chatbot.pregunta());
		return "chatbot";
	}
	
	
	@RequestMapping("/responderChatbot")
	public String respuestaChatbot(@RequestParam(required = true) String respuesta, Model model) {
		chatbot.responder(respuesta);
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
