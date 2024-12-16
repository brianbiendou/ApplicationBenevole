package fr.insa.mas.RequestService.resource;


import fr.insa.mas.RequestService.model.Request;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final List<Request> requests = new ArrayList<>();

    // Constructeur avec des demandes en mémoire pour l'exemple
    public RequestController() {
        requests.add(new Request(1L, "Collect Package", "Collect a package from the post office", "waiting", 101L));
        requests.add(new Request(2L, "Wash Clothes", "Help washing clothes in hospital", "validated", 102L));
    }

    // Endpoint pour obtenir toutes les demandes
    @GetMapping
    public List<Request> getAllRequests() {
        return requests;
    }

    // Endpoint pour obtenir une demande par son ID
    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requests.stream()
                .filter(request -> request.getId().equals(id))
                .findFirst()
                .orElse(null); // Ou lever une exception personnalisée si nécessaire
    }

    // Endpoint pour ajouter une demande
    @PostMapping
    public String addRequest(@RequestBody Request request) {
        requests.add(request);
        return "Request added successfully!";
    }

    // Endpoint pour mettre à jour une demande
    @PutMapping("/{id}")
    public String updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        for (Request request : requests) {
            if (request.getId().equals(id)) {
                request.setTitle(updatedRequest.getTitle());
                request.setDescription(updatedRequest.getDescription());
                request.setStatus(updatedRequest.getStatus());
                request.setUserId(updatedRequest.getUserId());
                return "Request updated successfully!";
            }
        }
        return "Request not found!";
    }

    // Endpoint pour supprimer une demande
    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable Long id) {
        boolean removed = requests.removeIf(request -> request.getId().equals(id));
        return removed ? "Request deleted successfully!" : "Request not found!";
    }
}
