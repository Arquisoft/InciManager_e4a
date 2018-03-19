package dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Asignada {
	
@Id @GeneratedValue public long id;
	
	@ManyToOne
	private Operario idOperario;
	
	@ManyToOne
	private Incidence idIncident;
	
	public Asignada() {		
	}

	public Asignada(Operario idOperario, Incidence idIncident) {
		super();
		this.idOperario = idOperario;
		this.idIncident = idIncident;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Operario getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Operario idOperario) {
		this.idOperario = idOperario;
	}

	public Incidence getIdIncident() {
		return idIncident;
	}

	public void setIdIncident(Incidence idIncident) {
		this.idIncident = idIncident;
	}

}
