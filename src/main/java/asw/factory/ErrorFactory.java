package asw.factory;


import asw.inciProcessor.webService.responses.errors.ErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidJsonErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidLabelsIncidenceErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidLocationIncidenceErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidMoreInfoIncidenceErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidPetitionErrorResponse;
import asw.inciProcessor.webService.responses.errors.InvalidPropertiesIncidenceErrorResponse;
import asw.inciProcessor.webService.responses.errors.KindDoNotMatchErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredDescriptionIncidenceErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredKindErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredLoginErrorResponse;
import asw.inciProcessor.webService.responses.errors.RequiredNameIncidenceErrorResponse;
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
		INCORRECT_PASSWORD_DO_NOT_MATCH,
		INCORRECT_KIND_DO_NOT_MATCH,
		REQUIRED_KIND,
		INVALID_JSON, 
		REQUIRED_NAMEINCIDENCE, 
		REQUIRED_DESCRIPTIONINCIDENCE, 
		INVALID_LOCATION,
		INVALID_PETITION, 
		INVALID_LABELS, 
		INVALID_MOREINFO, 
		INVALID_PROPERTIES

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
			return new UnknownErrorResponse();
		case REQUIRED_KIND:
			return new RequiredKindErrorResponse();
		case INCORRECT_KIND_DO_NOT_MATCH:
			return new KindDoNotMatchErrorResponse();
		case INVALID_JSON:
			return new InvalidJsonErrorResponse();	
		case REQUIRED_NAMEINCIDENCE:
			return new RequiredNameIncidenceErrorResponse();
		case REQUIRED_DESCRIPTIONINCIDENCE:
			return new RequiredDescriptionIncidenceErrorResponse();	
		case INVALID_LOCATION:
			return new InvalidLocationIncidenceErrorResponse();		
		case INVALID_PETITION:
			return new InvalidPetitionErrorResponse();
		case INVALID_LABELS:
			return new InvalidLabelsIncidenceErrorResponse();	
		case INVALID_MOREINFO:
			return new InvalidMoreInfoIncidenceErrorResponse();	
		case INVALID_PROPERTIES:
			return new InvalidPropertiesIncidenceErrorResponse();		
		default:// en caso de no conocer el error.
			return new UnknownErrorResponse();
		}
	}

}
