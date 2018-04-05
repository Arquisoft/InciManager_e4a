package asw.inciProcessor.webService.request;

import java.util.List;

import asw.dbManagement.model.LatLong;

public class PeticionNotifyIncidenceREST {
	
	private String login;
	private String password;
	private String name;
	private String description;
	private LatLong location;
	private List<String> labels;
	private asw.dbManagement.model.MapInfo mapInfo;

	
	public PeticionNotifyIncidenceREST(String login, String password, String name, String description, LatLong location,
			List<String> tags, asw.dbManagement.model.MapInfo fields) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.description = description;
		this.location = location;
		this.labels = tags;
		this.mapInfo = fields;
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

	public LatLong getLocation() {
		return location;
	}

	public void setLocation(LatLong location) {
		this.location = location;
	}

	public List<String> getTags() {
		return labels;
	}

	public void setTags(List<String> tags) {
		this.labels = tags;
	}

	public asw.dbManagement.model.MapInfo getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(asw.dbManagement.model.MapInfo mapInfo) {
		this.mapInfo = mapInfo;
	}	
	

}
