package asw.dbManagement.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import utils.IncidentProperties2Json;


@Entity
public class Incidence {

	@Id
	@GeneratedValue
	private Long id;

	private String inciName;
	private String inciDescription;

	private LatLong location;

	
	private String agent;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private Set<String> tags = new HashSet<String>();

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private List<String> moreInfo = new ArrayList<String>();

	@Convert(converter = IncidentProperties2Json.class)
	private Map<String, Object> properties = new HashMap<String, Object>();
	
	@Enumerated(EnumType.STRING)
	private State state;

	@OneToOne(mappedBy = "incidencia")
	private Notification notification;

	public Incidence() {
		
	}

	public Incidence(String name, LatLong location) {
		if (name.equals("") || location == null)
			throw new IllegalArgumentException("Incident fields cannot be empty");

		this.inciName = name;
		this.location = location;
	}

	public Incidence(String name, LatLong latLng, String agent) {
		this(name, latLng);
		this.setAgent(agent);
	}
	public Incidence(String agent, String nameIncidence, String descriptionIncidence) {
		this.agent = agent;
		this.inciName = nameIncidence;
		this.inciDescription = descriptionIncidence;
	}

	public Incidence(String name, LatLong latLng, String agent, String description) {
		this(name, latLng);
		this.setAgent(agent);
		this.setInciDescription(description);
	}

	public Incidence(String agent, String inciName, String inciDescription, LatLong location, Set<String> tags,
			List<String> moreInfo, Map<String, Object> properties) {
		this.agent = agent;
		this.inciName = inciName;
		this.inciDescription = inciDescription;
		this.location = location;
		this.tags = tags;
		this.moreInfo = moreInfo;
		this.properties = properties;
		setState(State.OPEN);
	}

	public void addMoreInfo(String info) {
		this.moreInfo.add(info);
	}

	public void addProperty(String property, Object value) {
		this.properties.put(property, value);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void setInciName(String inciName) {
		this.inciName = inciName;
	}

	public void setInciDescription(String inciDescription) {
		this.inciDescription = inciDescription;
	}

	public void setLocation(LatLong location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public String getInciName() {
		return inciName;
	}
	
	
	public String getInciDescription() {
		return inciDescription;
	}


	public LatLong getLocation() {
		return location;
	}

	public Set<String> getTags() {
		return tags;
	}

	public List<String> getMoreInfo() {
		return moreInfo;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void setMoreInfo(List<String> moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inciName == null) ? 0 : inciName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Incidence other = (Incidence) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Incidence [id=").append(id).append(", inciName=").append(inciName).append(", location=")
				.append(location).append(", tags=").append(tags).append(", moreInfo=").append(moreInfo)
				.append(", properties=").append(properties).append("]");
		return builder.toString();
	}

	public void addNot(Notification n1) {
		this.notification = n1;
	}
	
	public String tagList() {
		String cadena = "";
		for (String s : tags) {
			cadena +=s+",";
		}
		return cadena;
	}
	
	public boolean isOpen() {
		return this.state.equals(State.OPEN);
	}
	
	public boolean isInProg() {
		return this.state.equals(State.IN_PROCESS);
	}
	
	public boolean isClosed() {
		return this.state.equals(State.CLOSED);
	}
	
	public boolean isCancelled() {
		return this.state.equals(State.CANCELLED);
	}

	public void addTag(String etiqueta) {
		tags.add(etiqueta);
		
	}

}
