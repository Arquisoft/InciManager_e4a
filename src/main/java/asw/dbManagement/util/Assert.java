package asw.dbManagement.util;

import asw.factory.ErrorFactory;
import asw.factory.ErrorFactory.Errors;

public class Assert {
	
	
	
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
	
	
	
	/*public static boolean isKindCorrect(String kind,Agent agent){
		if(!Application.instancia.checkType(kind, agent)) {
			throw ErrorFactory.getError(Errors.INCORRECT_KIND_DO_NOT_MATCH);
		}
		int numTipo = instancia.obtainType(kind);
		agent.setType(numTipo);
		return true;
	}*/
	public static boolean isKindEmpty(String kind) {
		if(kind.trim().isEmpty())
			throw ErrorFactory.getError(Errors.REQUIRED_KIND);
		else
			return false;
		
	}

	
	
	
	
	


}
