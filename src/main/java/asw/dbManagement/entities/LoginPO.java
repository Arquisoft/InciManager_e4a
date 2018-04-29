package asw.dbManagement.entities;

public class LoginPO {
	public String login; 
	public String password;
	public String kind; 
	
	public LoginPO(String login, String password, String kind) {
		this.login = login;
		this.password = password;
		this.kind = kind;
	}
	
}
