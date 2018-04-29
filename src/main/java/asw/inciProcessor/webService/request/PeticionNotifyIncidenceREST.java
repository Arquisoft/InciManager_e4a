package asw.inciProcessor.webService.request;

import java.util.List;
import java.util.Map;

public class PeticionNotifyIncidenceREST {
	
	private String login;
	private String password;
	private String kind;
	private String name;
	private String description;
	private List<String> location;
	private String labels;
	private List<String> aditionalInfo;
	private Map<String, Object> mapInfo;

	public PeticionNotifyIncidenceREST() {
		
	}
	
	

	public PeticionNotifyIncidenceREST(String login, String password, String kind, String name, String description,
			List<String> location, String labels, List<String> aditionalInfo, Map<String, Object> properties) {
		super();
		this.login = login;
		this.password = password;
		this.kind = kind; 
		this.name = name;
		this.description = description;
		this.location = location;
		this.labels = labels;
		this.aditionalInfo = aditionalInfo;
		this.mapInfo = properties;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getLocation() {
		return location;
	}

	public void setLocation(List<String> location) {
		this.location = location;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public List<String> getMoreInfo() {
		return aditionalInfo;
	}

	public void setMoreInfo(List<String> moreInfo) {
		this.aditionalInfo = moreInfo;
	}

	public Map<String, Object> getProperties() {
		return mapInfo;
	}

	public void setProperties(Map<String, Object> properties) {
		this.mapInfo = properties;
	}
	
	public String getKind() {
		return kind; 
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

	
	

}
