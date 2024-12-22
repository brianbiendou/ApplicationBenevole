package fr.insa.mas.DemandeurService.resource;

import fr.insa.mas.DemandeurService.model.Demandeur;
import jakarta.transaction.Transactional;
import fr.insa.mas.DemandeurService.DataBase.DemandeurRepository;
import fr.insa.mas.DemandeurService.model.Demandeur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/demandeur")
public class DemandeurController {
	@Autowired
	private DemandeurRepository demandeurRepository;

    // Injection de RestTemplate pour les appels HTTP entre microservices
    @Autowired
    private RestTemplate restTemplate;

	
	// Endpoint pour obtenir tous les demandeurs
    @GetMapping("/get")
    public List<Demandeur> getAllDemandeur() {
    	return (List<Demandeur>) demandeurRepository.findAll();
    }


    // Endpoint pour obtenir un demandeur par son ID
    @GetMapping("/get/{id}")
    public Demandeur getDemandeurById(@PathVariable Long id) {
        return demandeurRepository.findById(id)
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }


 	// Endpoint pour ajouter un demandeur
    @PostMapping
    public String addDemandeur(@RequestBody Demandeur demand) {
    	String userServiceUrl = "http://localhost:8091/api/users/demand";
        demand.setId(Long.parseLong((String)(restTemplate.postForObject(userServiceUrl, demand, String.class))));
        demandeurRepository.save(demand);
        return "Demandeur added successfully!";
    }
    

    // Endpoint pour mettre à jour un demandeur
    @PutMapping("/update/{id}")
    public String updateDemand(@PathVariable Long id, @RequestBody Demandeur updatedDemand) {
        return demandeurRepository.findById(id)
                .map(demand -> {
                    demand.setName(updatedDemand.getName());
                    demand.setEmail(updatedDemand.getEmail());
                    demandeurRepository.save(demand); // Sauvegarde des modifications
                    return "Demandeur updated successfully!";
                })
                .orElse("Demandeur not found!"); // Si demandeur n'existe pas
    }




	// Endpoint pour supprimer un demandeur
    @DeleteMapping("/delete/{id}")
    public String deleteDemandeur(@PathVariable Long id) {
        if (demandeurRepository.existsById(id)) {
        	
        	
            demandeurRepository.deleteById(id);
            return "Demandeur deleted successfully!";
        } else {
            return "Demandeur not found!";
        }
    }

}