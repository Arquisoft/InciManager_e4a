package dbManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbManagement.model.Incidence;
import dbManagement.repository.IncidenceRepository;

@Service
public class IncidenceService {
	
	@Autowired
	private IncidenceRepository incidenceRepository;
	
	public List<Incidence> getIncidencesByUser(String user){
		return incidenceRepository.findByUser(user);
	}

}
