package asw.dbManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

@Entity(name = "Agent")
@Table(name = "Agent")
public class Agent {
	
	@Id
	private String iD;
	private String email;
	private String localizacion;
	private String nombre;
	private String password;
	private int type;
	
	@OneToMany(mappedBy="agent")
	  private List<Incidence> incidences;
	
	@OneToMany(
	        mappedBy = "agent",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private List<Assignment> incidencesAssignment = new ArrayList<>();

	public Agent() {}
	
	public Agent(String iD, String email, String localizacion, String nombre, String password, int type,
			List<Incidence> incidences, List<Assignment> incidencesAssignment) {
		super();
		this.iD = iD;
		this.email = email;
		this.localizacion = localizacion;
		this.nombre = nombre;
		this.password = password;
		this.type = type;
		this.incidences = incidences;
		this.incidencesAssignment = incidencesAssignment;
	}

	public String getID() {
		return iD;
	}

	public void setID(String iD) {
		iD = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(List<Incidence> incidences) {
		this.incidences = incidences;
	}

	public List<Assignment> getIncidencesAssignment() {
		return incidencesAssignment;
	}

	public void setIncidencesAssignment(List<Assignment> incidencesAssignment) {
		this.incidencesAssignment = incidencesAssignment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iD == null) ? 0 : iD.hashCode());
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
		Agent other = (Agent) obj;
		if (iD == null) {
			if (other.iD != null)
				return false;
		} else if (!iD.equals(other.iD))
			return false;
		return true;
	}
	
	

}
