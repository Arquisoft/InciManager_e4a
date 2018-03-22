package dbManagement.impl;

import org.springframework.stereotype.Service;

import dbManagement.IncidenceService;
import dbManagement.model.Incidence;
import dbManagement.repository.IncidenceRepository;

@Service
public class IncidenceServiceImpl implements IncidenceService{

	private IncidenceRepository repository;
	
	@Override
	public Incidence saveIncidence(Incidence incidence) {
		return repository.save(incidence);
	}
}
