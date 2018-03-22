package asw.factory;


import asw.inciProcessor.webService.responses.errors.ErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredLoginErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredPasswordErrorResponse;
import asw.inciProcessor.webService.responses.errors.UnknownErrorResponse;
import asw.inciProcessor.webService.responses.errors.UserNotFoundResponse;

//Creacion de los distintos tipos de error.
public class ErrorFactory {

	public static enum Errors {
		INCORRECT_LOGIN,
		REQUIRED_LOGIN,
		REQUIRED_PASSWORD,
		USER_NOT_FOUND,
		INCORRECT_PASSWORD_DO_NOT_MATCH

	}

	// Generar Constructor privado no queremos que se pueda tener varias
	// instancias de la clase.
	private ErrorFactory() {
	}

	public static ErrorResponse getError(Errors error) {
		switch (error) {
		
		case REQUIRED_PASSWORD:
			return new RequiredPasswordErrorResponse();
		case USER_NOT_FOUND:
			return new UserNotFoundResponse();
		case REQUIRED_LOGIN:
			return new RequiredLoginErrorResponse();
		case INCORRECT_LOGIN:
			return new UnknowErrorResponse();
		default:// en caso de no conocer el error.
			return new UnknownErrorResponse();
		}
	}

}
