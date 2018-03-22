package asw.dbManagement.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import scala.Serializable;

@Embeddable
public class AssignmentId implements Serializable {

	private static final long serialVersionUID = 1L;

		@Column(name = "idIncidence")
		private int idIncidence;
	 
	    @Column(name = "idAgent")
	    private String idAgent;
	 
	    @SuppressWarnings("unused")
		private AssignmentId() {}

		public AssignmentId(int idIncidence, String idAgent) {
			super();
			this.idIncidence = idIncidence;
			this.idAgent = idAgent;
		}



		public int getIdIncidence() {
			return idIncidence;
		}

		public void setIdIncidence(int idIncidence) {
			this.idIncidence = idIncidence;
		}

		public String getIdAgent() {
			return idAgent;
		}

		public void setIdAgent(String idAgent) {
			this.idAgent = idAgent;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idAgent == null) ? 0 : idAgent.hashCode());
			result = prime * result + idIncidence;
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
			AssignmentId other = (AssignmentId) obj;
			if (idAgent == null) {
				if (other.idAgent != null)
					return false;
			} else if (!idAgent.equals(other.idAgent))
				return false;
			if (idIncidence != other.idIncidence)
				return false;
			return true;
		}
	 
	   

}
