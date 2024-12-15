package fr.insa.mas.AdminService.resource;

import fr.insa.mas.AdminService.DataBase.AdminRepository;
import fr.insa.mas.AdminService.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;

    private final List<Admin> admins = new ArrayList<>();

    // Constructeur avec des administrateurs en mémoire pour l'exemple
    public AdminController() {
        admins.add(new Admin(1L, "Super Admin", "superadmin@example.com"));
        admins.add(new Admin(2L, "Manager Admin", "manageradmin@example.com"));
    }

    // Endpoint pour obtenir tous les administrateurs
    @GetMapping
    public List<Admin> getAllAdmins() {
        return admins;
    }

    // Endpoint pour obtenir un administrateur par son ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return admins.stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst()
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoint pour ajouter un administrateur
    @PostMapping
    public String addAdmin(@RequestBody Admin admin) {
    	adminRepository.save(admin);
        admins.add(admin);
        return "Admin added successfully!";
    }
    
    
    

    // Endpoint pour mettre à jour un administrateur
    @PutMapping("/{id}")
    public String updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
                admin.setName(updatedAdmin.getName());
                admin.setEmail(updatedAdmin.getEmail());
                return "Admin updated successfully!";
            }
        }
        return "Admin not found!";
    }

    // Endpoint pour supprimer un administrateur
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        boolean removed = admins.removeIf(admin -> admin.getId().equals(id));
        return removed ? "Admin deleted successfully!" : "Admin not found!";
    }
}