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
    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll(); // Retourne tous les utilisateurs depuis la BD
    }

    // Endpoint pour obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoint pour ajouter un utilisateur
    @PostMapping
    public String addUser(@RequestBody User user) {
        // Sauvegarder l'utilisateur dans la table user
        userRepository.save(user);

        // Si l'utilisateur est un admin, ajoutez-le au microservice Admin
        if ("admin".equals(user.getRole())) {
        	System.out.println("********************************************************************************************************************************");
            // URL de l'API AdminService pour ajouter un admin
            String adminServiceUrl = "http://insa-20929.insa-toulouse.fr:8094/api/admins";
            restTemplate.postForObject(adminServiceUrl, user, User.class);
        }

        // Si l'utilisateur est un volunteer, ajoutez-le au microservice Volunteer
        if ("volunteer".equals(user.getRole())) {
            // URL de l'API VolunteerService pour ajouter un volunteer
            String volunteerServiceUrl = "http://insa-20929.insa-toulouse.fr:8093/api/volunteers";
            restTemplate.postForObject(volunteerServiceUrl, user, User.class);
            System.out.println("#########################################################################################################################################");
        }

        return "User added successfully!";
    }

    // Endpoint pour mettre à jour un utilisateur
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get()); // Supprime l'utilisateur de la BD

            // Supprimer l'utilisateur des microservices correspondants
            String adminServiceUrl = "http://insa-20929.insa-toulouse.fr:8094/api/admins/" + id;
            String volunteerServiceUrl = "http://insa-20929.insa-toulouse.fr:8093/api/volunteers/" + id;

            restTemplate.delete(adminServiceUrl);
            restTemplate.delete(volunteerServiceUrl);

            return "User deleted successfully!";
        }
        return "User not found!";
    }
}
