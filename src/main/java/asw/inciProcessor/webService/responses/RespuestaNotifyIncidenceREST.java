package asw.inciProcessor.webService.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;
import asw.dbManagement.entities.State;

@XmlRootElement(name = "incidence")
public class RespuestaNotifyIncidenceREST {
	
	private String name;
	private LatLong location;
	private State status;
	private Long id;
	private String operator;
	
	public RespuestaNotifyIncidenceREST() {}
	
	public RespuestaNotifyIncidenceREST(Incidence incidence, String operator) 
	{
		setName(incidence.getInciName());
		setLocation(incidence.getLocation());
		setStatus(incidence.getState());
		setId(incidence.getId());
		setOperator(operator);
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public LatLong getLocation() {
		return location;
	}

	@XmlElement
	public void setLocation(LatLong latLong) {
		this.location = latLong;
	}

	public State getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(State state) {
		this.status = state;
	}
	
	public String getOperator() {
		return operator;
	}
	
	@XmlElement
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Long getId() {
		return id;
	}

	@XmlElement
	public void setId(Long long1) {
		this.id = long1;
	}
	
	
}
