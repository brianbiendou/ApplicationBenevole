package fr.insa.mas.UserService.DataBase;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import fr.insa.mas.UserService.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
