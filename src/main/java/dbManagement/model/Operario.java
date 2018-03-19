package dbManagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Operario {
	
	@Id @GeneratedValue public long id;
	private String name;
	private String type;
	
	@OneToMany(mappedBy = "idOperario", cascade = {CascadeType.ALL })	
	private Set<Asignada> incidencias = new HashSet<Asignada>();
	
	public Operario(){
		
	}

	public Operario(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Asignada> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Set<Asignada> incidencias) {
		this.incidencias = incidencias;
	}

}
