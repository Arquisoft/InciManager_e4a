package asw.dbManagement.repositories;

import org.springframework.data.repository.CrudRepository;

import asw.dbManagement.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long>{

	Operator findByid(Long identificador);

}
