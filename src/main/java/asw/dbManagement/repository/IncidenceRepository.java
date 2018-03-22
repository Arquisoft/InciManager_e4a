package asw.dbManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asw.dbManagement.model.Incidence;

@Repository
public interface IncidenceRepository extends CrudRepository<Incidence, Long>{
	
	List<Incidence> findByUser(String user);

}
