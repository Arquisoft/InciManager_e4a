package asw.dbManagement.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import asw.dbManagement.util.Status;

@Entity(name = "Incidence")
@Table(name = "Incidence")
public class Incidence {
	
	@Id @GeneratedValue public int id;
	
	private String login;
	private String password;
	private String name;
	private String description;
	private String location;
	private Date expiration;
	private Status state;
	
	@ManyToOne
	 private HashMap hashMaps;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_agent")
	private Agent agent;

	@OneToMany(
	        mappedBy = "incidence", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private List<Assignment> agentsAssignment = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "Incidence-Category",
            joinColumns = { @JoinColumn(name = "idIncidence") },
            inverseJoinColumns = { @JoinColumn(name = "idCategory") })
    private Set<Category> categories = new HashSet<>();
	
	public Incidence() {}
	
	public Incidence(String name, String description, String location, Date expiration, Status state,
			List<HashMap> hashMaps, Agent agent, List<Assignment> agentsAssignment) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.expiration = expiration;
		this.state = state;
		//this.hashMaps = hashMaps;
		this.agent = agent;
		this.agentsAssignment = agentsAssignment;
	}

	public Incidence(String login, String password, String name2, String description2, String location2,
			List<String> tags, HashMap fields) {
		super();
		this.login=login;
		this.password=password;
		this.name=name2;
		this.description=description2;
		this.location=location2;
		this.categories=new HashSet<>();
		this.hashMaps=fields;
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Status getState() {
		return state;
	}

	public void setState(Status state) {
		this.state = state;
	}

	public HashMap getHashMaps() {
		return hashMaps;
	}

	public void setHashMaps(HashMap hashMaps) {
		this.hashMaps = hashMaps;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Assignment> getAssignments() {
		return agentsAssignment;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.agentsAssignment = assignments;
	}

	public List<Assignment> getAgentsAssignment() {
		return agentsAssignment;
	}

	public void setAgentsAssignment(List<Assignment> agentsAssignment) {
		this.agentsAssignment = agentsAssignment;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void addAgent(Agent agent) {
        Assignment assignment = new Assignment(this, agent);
        agentsAssignment.add(assignment);
        agent.getIncidencesAssignment().add(assignment);
    }
 
    public void removeAgent(Agent agent) {
    	for (Iterator<Assignment> iterator = agentsAssignment.iterator(); 
                iterator.hasNext(); ) {
    		Assignment assign = iterator.next();
    		if (assign.getIncidence().equals(this) &&
    				assign.getAgent().equals(agent)) {
                iterator.remove();
                assign.getAgent().getIncidencesAssignment().remove(assign);
                assign.setIncidence(null);
                assign.setAgent(null);
            }
    	}
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
		Incidence other = (Incidence) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
