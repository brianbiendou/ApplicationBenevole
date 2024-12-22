package fr.insa.mas.DemandeurService.DataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.insa.mas.DemandeurService.model.Demandeur;

@Repository
public interface DemandeurRepository extends CrudRepository<Demandeur,Long>{
	

}
