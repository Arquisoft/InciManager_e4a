package asw.dbManagement.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "Assignment")
@Table(name = "Assignment")
public class Assignment {
	
	@EmbeddedId
    private AssignmentId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idIncidence")
    private Incidence incidence;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAgent")
    private Agent agent;
 
    @Column(name = "comments")
    private String comments = new String();

    public Assignment() {}
    
	public Assignment(Incidence incidence, Agent agent) {
		super();
		this.incidence = incidence;
		this.agent = agent;
		this.id = new AssignmentId(incidence.getId(), agent.getID());
	}

	public Incidence getIncidence() {
		return incidence;
	}

	public void setIncidence(Incidence incidence) {
		this.incidence = incidence;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + ((incidence == null) ? 0 : incidence.hashCode());
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
		Assignment other = (Assignment) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (incidence == null) {
			if (other.incidence != null)
				return false;
		} else if (!incidence.equals(other.incidence))
			return false;
		return true;
	}
    
    

}
