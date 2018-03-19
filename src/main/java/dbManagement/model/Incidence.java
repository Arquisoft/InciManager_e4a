package dbManagement.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dbManagement.util.Status;

@Entity
public class Incidence {
	
	@Id @GeneratedValue public long id;
	
	private String user;
	private String password;
	private String name;
	private String description;
	private String location;
	
	@ElementCollection(targetClass=String.class)
	private List<String> tags;
	
	private HashMap<String, String> fields;
	
	private Status status;
	private Date expiration;
	
	@OneToMany(mappedBy = "idIncident", cascade = {CascadeType.ALL })	
	private Set<Asignada> operarios = new HashSet<Asignada>();
	
	public Incidence() {
		
	}

	public Incidence(String user, String password, String name, String descripcion, String location, List<String> etiquetas) {
		super();
		this.user = user;
		this.password = password;
		this.name = name;
		this.description = descripcion;
		this.location = location;
		this.tags = etiquetas;
		this.status = Status.OPENED;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getDescripcion() {
		return description;
	}

	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public HashMap<String, String> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, String> fields) {
		this.fields = fields;
	}

	public Set<Asignada> getOperarios() {
		return operarios;
	}

	public void setOperarios(Set<Asignada> operarios) {
		this.operarios = operarios;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + id + ", user=" + user + ", password=" + password + ", name=" + name + ", description="
				+ description + ", location=" + location + ", tags=" + tags + ", fields=" + fields + ", status="
				+ status + ", expiration=" + expiration + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}	

}
