package asw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.repositories.IncidenceRepository;

@Service	
public class IncidencesService {
	
	@Autowired
	IncidenceRepository incidenceRepository;
	
	public Incidence addIncidence(Incidence incidencia) {
		return incidenceRepository.save(incidencia);
		
	}

}
