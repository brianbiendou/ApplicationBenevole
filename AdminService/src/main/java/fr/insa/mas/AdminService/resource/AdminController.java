package fr.insa.mas.AdminService.resource;

import fr.insa.mas.AdminService.DataBase.AdminRepository;
import fr.insa.mas.AdminService.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminRepository adminRepository;

    // Endpoint pour obtenir tous les administrateurs
    @GetMapping
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }


    // Endpoint pour obtenir un administrateur par son ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }


 	// Endpoint pour ajouter un administrateur
    @PostMapping
    public String addAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
        return "Admin added successfully!";
    }

    
    
    

    // Endpoint pour mettre à jour un administrateur
    @PutMapping("/{id}")
    public String updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        return adminRepository.findById(id)
                .map(admin -> {
                    admin.setName(updatedAdmin.getName());
                    admin.setEmail(updatedAdmin.getEmail());
                    adminRepository.save(admin); // Sauvegarde des modifications
                    return "Admin updated successfully!";
                })
                .orElse("Admin not found!"); // Si l'administrateur n'existe pas
    }


    // Endpoint pour supprimer un administrateur
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return "Admin deleted successfully!";
        } else {
            return "Admin not found!";
        }
    }

}