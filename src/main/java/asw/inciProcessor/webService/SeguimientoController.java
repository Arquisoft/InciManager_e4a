package asw.inciProcessor.webService;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeguimientoController {

	@RequestMapping("/")
	public String inicalicerLogin(Model model) {
		return "seguimientoIncidencias";
	}
}