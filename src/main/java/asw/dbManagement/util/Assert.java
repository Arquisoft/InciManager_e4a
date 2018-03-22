package asw.dbManagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asw.Application;
import asw.dbManagement.model.Agent;
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
	/**
	 * Metodo que comprueba si el agente es null
	 * @param agent
	 * @return devuelve false si no es null o excepcion
	 */
	public static boolean isAgentNull(Agent agent){
		if (agent == null) {
			throw ErrorFactory.getError(Errors.USER_NOT_FOUND);
		}
		return false;		
	}
	
	/**
	 * Metodo que comprueba que la contraseña sea correcta
	 * @param password
	 * @param agent
	 * @return excepcion si es incorrecta, true si es correcta
	 */
	public static boolean isPasswordCorrect(String password,Agent agent){
		if (!password.equals(agent.getPassword())) {
			throw ErrorFactory.getError(Errors.INCORRECT_PASSWORD_DO_NOT_MATCH);
		}
		return true;
	}

	
	
	
	
	


}
