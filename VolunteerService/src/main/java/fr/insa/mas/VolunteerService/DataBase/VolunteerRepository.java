package fr.insa.mas.VolunteerService.DataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.insa.mas.VolunteerService.model.Volunteer;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

}
