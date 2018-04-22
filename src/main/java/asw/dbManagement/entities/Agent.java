package asw.dbManagement.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Agent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String dni;
	private String email;
	private LatLong localizacion;
	private String nombre;
	private String password;
	private int type;
	
	@OneToMany
	  private List<Incidence> incidences;
	
	public Agent() {}
	
	public Agent(String dni, String email, LatLong localizacion, String nombre, String password, int type,
			List<Incidence> incidences) {
		
		this.dni = dni;
		this.email = email;
		this.localizacion = localizacion;
		this.nombre = nombre;
		this.password = password;
		this.type = type;
		this.incidences = incidences;
	}
	
	public Agent(String dni, String email, LatLong localizacion, String nombre, String password, int type) {
		
		this.dni = dni;
		this.email = email;
		this.localizacion = localizacion;
		this.nombre = nombre;
		this.password = password;
		this.type = type;
	}

	
	public Long getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LatLong getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LatLong localizacion) {
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
	public void addIncidence(Incidence incidencia1) {
		incidences.add(incidencia1);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
