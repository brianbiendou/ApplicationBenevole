package fr.insa.mas.VolunteerService.resource;


import fr.insa.mas.VolunteerService.model.Volunteer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final List<Volunteer> volunteers = new ArrayList<>();

    // Constructeur avec des volontaires en mémoire pour l'exemple
    public VolunteerController() {
        volunteers.add(new Volunteer(1L, "Alice Brown", "alice.brown@example.com", "123456789"));
        volunteers.add(new Volunteer(2L, "Bob White", "bob.white@example.com", "987654321"));
    }

    // Endpoint pour obtenir tous les volontaires
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteers;
    }

    // Endpoint pour obtenir un volontaire par son ID
    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        return volunteers.stream()
                .filter(volunteer -> volunteer.getId().equals(id))
                .findFirst()
                .orElse(null); // lever une exception 
    }

    // Endpoint pour ajouter un volontaire
    @PostMapping
    public String addVolunteer(@RequestBody Volunteer volunteer) {
        volunteers.add(volunteer);
        return "Volunteer added successfully!";
    }

    // Endpoint pour mettre à jour un volontaire
    @PutMapping("/{id}")
    public String updateVolunteer(@PathVariable Long id, @RequestBody Volunteer updatedVolunteer) {
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getId().equals(id)) {
                volunteer.setName(updatedVolunteer.getName());
                volunteer.setEmail(updatedVolunteer.getEmail());
                volunteer.setPhoneNumber(updatedVolunteer.getPhoneNumber());
                return "Volunteer updated successfully!";
            }
        }
        return "Volunteer not found!";
    }

    // Endpoint pour supprimer un volontaire
    @DeleteMapping("/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        boolean removed = volunteers.removeIf(volunteer -> volunteer.getId().equals(id));
        return removed ? "Volunteer deleted successfully!" : "Volunteer not found!";
    }
}
