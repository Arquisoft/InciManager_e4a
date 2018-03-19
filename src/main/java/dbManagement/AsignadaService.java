package dbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbManagement.repository.AsignadaRepository;

@Service
public class AsignadaService {
	
	@Autowired
	private AsignadaRepository asignadaRepository;

}
