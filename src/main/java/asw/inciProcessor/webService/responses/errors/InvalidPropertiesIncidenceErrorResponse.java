package asw.inciProcessor.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Properties invalid")
public class InvalidPropertiesIncidenceErrorResponse  extends ErrorResponse{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessageJSONFormat() {
		// TODO Auto-generated method stub
		return "{\"reason\": \"Properties invalid\"}";
	}

	@Override
	public String getMessageStringFormat() {
		// TODO Auto-generated method stub
		return "Properties invalid";
	}
}
