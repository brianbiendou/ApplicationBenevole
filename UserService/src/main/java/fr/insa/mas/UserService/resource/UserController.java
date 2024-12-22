package fr.insa.mas.UserService.resource;

import fr.insa.mas.UserService.model.User;
import fr.insa.mas.UserService.DataBase.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    // Injection du repository via le constructeur
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Injection de RestTemplate pour les appels HTTP entre microservices
    @Autowired
    private RestTemplate restTemplate;

    // Endpoint pour obtenir tous les utilisateurs
    @GetMapping("/get")
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll(); // Retourne tous les utilisateurs depuis la BD
    }

    // Endpoint pour obtenir un utilisateur par son ID
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        System.out.println(user);
        return user.orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoints pour ajouter un utilisateur
    @PostMapping
    public String addUser(@RequestBody User user) {
        // Sauvegarder l'utilisateur dans la table user
    	if (user.getRole()!= null) {
    		if (user.getRole().equals("demandeur")) {
    			String demandServiceUrl = "http://localhost:8095/api/demandeur";
    	        restTemplate.postForObject(demandServiceUrl, user, String.class);
    	        return "Appel ajout demandeur";
    		}
    		else if (user.getRole().equals("admin")) {
    			String adminServiceUrl = "http://localhost:8094/api/admin";
    	        restTemplate.postForObject(adminServiceUrl, user, String.class);
    	        return "Appel ajout admin";
    		}
    		else if (user.getRole().equals("volunteer")) {
    			String volunServiceUrl = "http://localhost:8093/api/volunteers";
    	        restTemplate.postForObject(volunServiceUrl, user, String.class);
    	        return "Appel ajout volunteer";
    		}
    	}
        return "Invalid role";
    }
    
    
    @PostMapping("/demand")
    public long addDemand(@RequestBody User user) {
        // Sauvegarder l'utilisateur dans la table user
    	user.setRole("demandeur");
        userRepository.save(user);
        return user.getId();
    }
    @PostMapping("/admin")
    public long addAdmin(@RequestBody User user) {
        // Sauvegarder l'utilisateur dans la table user
    	user.setRole("admin");
        userRepository.save(user);
        return user.getId();
    }
    @PostMapping("/volunteer")
    public long addVolunteer(@RequestBody User user) {
        // Sauvegarder l'utilisateur dans la table user
    	user.setRole("volunteer");
        userRepository.save(user);
        return user.getId();
    }

    // Endpoint pour mettre à jour un utilisateur
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            userRepository.save(user); // Met à jour l'utilisateur dans la BD

            // Mise à jour des microservices si le rôle est modifié
            if ("admin".equals(user.getRole())) {
                String adminServiceUrl = "http://insa-20929.insa-toulouse.fr:8094/api/admins";
                restTemplate.put(adminServiceUrl + "/" + id, user);
            }

            if ("volunteer".equals(user.getRole())) {
                String volunteerServiceUrl = "http://insa-20929.insa-toulouse.fr:8093/api/volunteers";
                restTemplate.put(volunteerServiceUrl + "/" + id, user);
            }

            return "User updated successfully!";
        }
        return "User not found!";
    }

    // Endpoint pour supprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
        	if (existingUser.get().getRole() != null) {
        		
	        	if(existingUser.get().getRole().equals("demandeur")) {
	        		String demandeurServiceUrl = "http://localhost:8095/api/demandeur/delete/" + id;
	                restTemplate.delete(demandeurServiceUrl);
	        	}
	        	else if (existingUser.get().getRole().equals("admin")) {
	                String adminServiceUrl = "http://localhost:8094/api/admin/delete/" + id;
	                restTemplate.delete(adminServiceUrl);        
	        	}
	        	else if (existingUser.get().getRole().equals("volunteer")) {
	        		String volunteerServiceUrl = "http://localhost:8093/api/volunteers/delete/" + id;
	        		restTemplate.delete(volunteerServiceUrl);
	        	}
        	}
            userRepository.delete(existingUser.get()); // Supprime l'utilisateur de la BD
            
            // Supprimer l'utilisateur des microservices correspondants

            return "User deleted successfully!";
        }
        return "User not found!";
    }
}
