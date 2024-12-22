package fr.insa.mas.AdminService.resource;

import fr.insa.mas.AdminService.DataBase.AdminRepository;
import fr.insa.mas.AdminService.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private AdminRepository adminRepository;

    // Endpoint pour obtenir tous les administrateurs
	@Autowired
	public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
	
	@Autowired
    private RestTemplate restTemplate;

	
    @GetMapping("/get")
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }


    // Endpoint pour obtenir un administrateur par son ID
    @GetMapping("/get/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }


 	// Endpoint pour ajouter un administrateur
    @PostMapping
    public String addAdmin(@RequestBody Admin admin) {
    	String userServiceUrl = "http://localhost:8091/api/users/admin";
        admin.setId(Long.parseLong((String)(restTemplate.postForObject(userServiceUrl, admin, String.class))));
        adminRepository.save(admin);
        return "Admin added successfully!";
    }

    
    
    

    // Endpoint pour mettre à jour un administrateur
    @PutMapping("/update/{id}")
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
    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return "Admin deleted successfully!";
        } else {
            return "Admin not found!";
        }
    }

}