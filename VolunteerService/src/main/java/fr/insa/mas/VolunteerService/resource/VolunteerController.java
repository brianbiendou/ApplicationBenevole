package fr.insa.mas.VolunteerService.resource;

import fr.insa.mas.VolunteerService.model.Volunteer;
import fr.insa.mas.VolunteerService.DataBase.VolunteerRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerRepository volunteerRepository;

    // Injection du repository via le constructeur
    @Autowired
    public VolunteerController(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    // Endpoint pour obtenir tous les volontaires
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return (List<Volunteer>) volunteerRepository.findAll(); // Retourne tous les volontaires depuis la BD
    }

    // Endpoint pour obtenir un volontaire par son ID
    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        return volunteer.orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoint pour ajouter un volontaire
    @PostMapping("/add")
    public String addVolunteer(@RequestBody Volunteer volunteer) {
        volunteerRepository.save(volunteer); // Sauvegarde le volontaire dans la BD
        return "Volunteer added successfully!";
    }

    // Endpoint pour mettre à jour un volontaire
    @PutMapping("/{id}")
    public String updateVolunteer(@PathVariable Long id, @RequestBody Volunteer updatedVolunteer) {
        Optional<Volunteer> existingVolunteer = volunteerRepository.findById(id);
        if (existingVolunteer.isPresent()) {
            Volunteer volunteer = existingVolunteer.get();
            volunteer.setName(updatedVolunteer.getName());
            volunteer.setEmail(updatedVolunteer.getEmail());
            volunteer.setPhoneNumber(updatedVolunteer.getPhoneNumber());
            volunteerRepository.save(volunteer); // Met à jour le volontaire dans la BD
            return "Volunteer updated successfully!";
        }
        return "Volunteer not found!";
    }

    // Endpoint pour supprimer un volontaire
    @DeleteMapping("/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        Optional<Volunteer> existingVolunteer = volunteerRepository.findById(id);
        if (existingVolunteer.isPresent()) {
            volunteerRepository.delete(existingVolunteer.get()); // Supprime le volontaire de la BD
            return "Volunteer deleted successfully!";
        }
        return "Volunteer not found!";
    }
}
