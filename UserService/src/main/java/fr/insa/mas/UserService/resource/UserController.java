package fr.insa.mas.UserService.resource;


import fr.insa.mas.UserService.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = new ArrayList<>();

    // Constructeur avec quelques utilisateurs en mémoire pour l'exemple
    public UserController() {
        users.add(new User(1L, "brian biendou", "brianbiendou@example.com", "user"));
        users.add(new User(2L, "lebron James", "lebronjames@example.com", "volunteer"));
        users.add(new User(3L, "Admin User", "admin@example.com", "admin"));
    }

    // Endpoint pour obtenir tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Endpoint pour obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoint pour ajouter un utilisateur
    @PostMapping
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "User added successfully!";
    }

    // Endpoint pour mettre à jour un utilisateur
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setRole(updatedUser.getRole());
                return "User updated successfully!";
            }
        }
        return "User not found!";
    }

    // Endpoint pour supprimer un utilisateur
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        return removed ? "User deleted successfully!" : "User not found!";
    }
}
