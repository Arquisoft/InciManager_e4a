package asw.dbManagement.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "HashMap")
public class Category {

	@Id
	@GeneratedValue
	public int id;
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "Incidence-Category",
            joinColumns = { @JoinColumn(name = "idCategory") },
            inverseJoinColumns = { @JoinColumn(name = "idIncidence") })
    private Set<Incidence> incidences = new HashSet<>();

	public Category() {}
	
	public Category(String name, Set<Incidence> incidences) {
		super();
		this.name = name;
		this.incidences = incidences;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(Set<Incidence> incidences) {
		this.incidences = incidences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	} 

	
}
