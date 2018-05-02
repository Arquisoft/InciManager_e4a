package asw.dbManagement.util;

import java.util.List;

import com.google.gson.Gson;

import asw.factory.ErrorFactory;
import asw.factory.ErrorFactory.Errors;

public class Assert {
	
	private static final Gson gson = new Gson();
	
	public static boolean isLoginEmpty(String login) {
		if(login.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_LOGIN);
		else
			return false;
	}
	
	public static boolean isPasswordEmpty(String password) {
		if(password.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_PASSWORD);
		else
			return false;
	}
	

	public static boolean isKindEmpty(String kind) {
		if(kind.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_KIND);
		else
			return false;
		
	}
	
	public static boolean isJSONInvalid(String jsonInString) {
		try {
	          gson.fromJson(jsonInString, Object.class);
	          
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	    	  throw ErrorFactory.getError(Errors.INVALID_JSON);
	      }
		return false;
	}

	public static boolean isNameIncidenceEmpty(String name) {
		if(name.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_NAMEINCIDENCE);
		else
			return false;
		
	}
	
	public static boolean isDescriptionIncidenceEmpty(String description) {
		if(description.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_DESCRIPTIONINCIDENCE);
		else
			return false;
		
	}
	
	public static boolean isLocationIncidenceInvalid(List<String> location) {
		if(location.size()!=2)
			throw ErrorFactory.getError(Errors.INVALID_LOCATION);
		else if(location.get(0).toString().trim().isEmpty())
			throw ErrorFactory.getError(Errors.INVALID_LOCATION);
		else if(location.get(1).toString().trim().isEmpty())
			throw ErrorFactory.getError(Errors.INVALID_LOCATION);
		else if(location.get(0).toString().matches("-?\\d+(\\.\\d+)?"))
			throw ErrorFactory.getError(Errors.INVALID_LOCATION);
		else if(location.get(1).toString().matches("-?\\d+(\\.\\d+)?"))
			throw ErrorFactory.getError(Errors.INVALID_LOCATION);
		else
			return false;
		
	}
	
	
	
	


}
