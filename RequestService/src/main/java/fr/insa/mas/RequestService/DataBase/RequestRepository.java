package fr.insa.mas.RequestService.DataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.insa.mas.RequestService.model.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request,Long>{
	

}
