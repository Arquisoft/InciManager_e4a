package asw.dbManagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbManagement.entities.Incidence;

@Repository
public interface IncidenceRepository extends CrudRepository<Incidence, Long>{
	

	
}
