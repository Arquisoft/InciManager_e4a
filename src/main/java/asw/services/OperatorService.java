package asw.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Operator;
import asw.dbManagement.repositories.OperatorRepository;

@Service
public class OperatorService {
	
	@Autowired
	OperatorRepository operatorRepository;
	
	public void addOperator(Operator op) {
		operatorRepository.save(op);		
	}	

	public Operator getOperator(Long identificador) {
		
		return operatorRepository.findByid(identificador);
	}
	
	public List<Operator> getOperators(){
		List<Operator> operators = new ArrayList<Operator>();
		operatorRepository.findAll().forEach(operators::add);
		return operators;
	}
	
	public Operator assignOperator() {
		List<Operator> operators=getOperators();
		Random random = new Random();
		int num = random.nextInt(operators.size()-1)+1;
		return operatorRepository.findByid(Long.valueOf(num));			
	}

	public void updateOperator(Operator op1) {
		operatorRepository.save(op1);
		
	}
}
