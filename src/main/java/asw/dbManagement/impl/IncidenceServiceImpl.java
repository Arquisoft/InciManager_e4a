package asw.dbManagement.impl;

import org.springframework.stereotype.Service;

import asw.dbManagement.IncidenceService;
import asw.dbManagement.model.Incidence;
import asw.dbManagement.repository.IncidenceRepository;

@Service
public class IncidenceServiceImpl implements IncidenceService{

	private IncidenceRepository repository;
	
	@Override
	public Incidence saveIncidence(Incidence incidence) {
		return repository.save(incidence);
	}
}
