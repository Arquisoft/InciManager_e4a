package asw.inciProcessor.webService.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import asw.dbManagement.model.Incidence;
import asw.dbManagement.util.Status;

@XmlRootElement(name = "incidence")
public class RespuestaNotifyIncidenceREST {
	
	private String name;
	private String location;
	private Status status;
	private int id;
	
	public RespuestaNotifyIncidenceREST() {}
	
	public RespuestaNotifyIncidenceREST(Incidence incidence) 
	{
		setName(incidence.getName());
		setLocation(incidence.getLocation());
		setStatus(incidence.getState());
		setId(incidence.getId());
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	@XmlElement
	public void setLocation(String location) {
		this.location = location;
	}

	public Status getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(Status status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	
}
