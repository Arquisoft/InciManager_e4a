package dbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbManagement.repository.OperarioRepository;

@Service
public class OperarioService {
	
	 @Autowired
	 private OperarioRepository operarioRepository;

}
