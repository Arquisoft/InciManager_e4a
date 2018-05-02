package asw.inciProcessor.webService.responses.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Labels invalid")
public class InvalidLabelsIncidenceErrorResponse extends ErrorResponse{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessageJSONFormat() {
		// TODO Auto-generated method stub
		return "{\"reason\": \"Labels invalid\"}";
	}

	@Override
	public String getMessageStringFormat() {
		// TODO Auto-generated method stub
		return "Labels invalid";
	}

}
