package asw.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Incidence> getIncidences() {
		List<Incidence> in = new ArrayList<Incidence>(); 
		incidenceRepository.findAll().forEach(p -> in.add(p));
		return in;
		
	}

}
