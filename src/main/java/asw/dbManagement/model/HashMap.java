package asw.dbManagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HashMap")
public class HashMap {

	@Id
	@GeneratedValue
	public int idHashMap;
	private String property;
	private String value;

	@OneToMany(
	        mappedBy = "hashMaps", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private Incidence incidence;

	public HashMap() {
	}

	public HashMap(String property, String value, Incidence incidence) {
		super();
		this.property = property;
		this.value = value;
		this.incidence = incidence;
	}

	public int getIdHashMap() {
		return idHashMap;
	}

	public void setIdHashMap(int idHashMap) {
		this.idHashMap = idHashMap;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Incidence getIncidence() {
		return incidence;
	}

	public void setIncidence(Incidence incidence) {
		this.incidence = incidence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idHashMap;
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
		HashMap other = (HashMap) obj;
		if (idHashMap != other.idHashMap)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HashMap [idHashMap=" + idHashMap + ", property=" + property + ", value=" + value + ", incidence="
				+ incidence + "]";
	}

}
