package asw.dbManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbManagement.entities.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {

	Agent findByDni(String identificador);
	

}
