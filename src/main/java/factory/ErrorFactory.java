package factory;

import asw.inciProcessor.webService.responses.errors.ErrorResponse;
import asw.inciProcessor.webService.responses.errors.UnknownErrorResponse;

//Creacion de los distintos tipos de error.
public class ErrorFactory {

	public static enum Errors {
		INCORRECT_LOGIN;
		
	}

	// Generar Constructor privado no queremos que se pueda tener varias
	// instancias de la clase.
	private ErrorFactory() {
	}

	public static ErrorResponse getError(Errors error) {
		switch (error) {
		case INCORRECT_LOGIN:
			return new UnknownErrorResponse();
		
		default:// en caso de no conocer el error.
			return new UnknownErrorResponse();
		}
	}

}
