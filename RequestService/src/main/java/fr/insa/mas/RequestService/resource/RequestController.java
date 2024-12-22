package fr.insa.mas.RequestService.resource;


import fr.insa.mas.RequestService.DataBase.RequestRepository;
import fr.insa.mas.RequestService.model.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
	@Autowired
	private RequestRepository requestRepository;

    private final List<Request> requests = new ArrayList<>();

    public RequestController() {
      
    }

    // Endpoint pour obtenir toutes les demandes
    @GetMapping("/get")
    public List<Request> getAllRequests() {
        return requests;
    }

    // Endpoint pour obtenir une demande par son ID
    @GetMapping("/get/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    // Endpoint pour ajouter une demande
    @PostMapping
    public String addRequest(@RequestBody Request request) {
        requestRepository.save(request);
        return "Request added successfully!";
    }

    // Endpoint pour mettre à jour une demande
    @PutMapping("/update/{id}")
    public String updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
    	return requestRepository.findById(id)
                .map(request -> {
                    request.setState(updatedRequest.getState());
                    request.setDescription(updatedRequest.getDescription());
                    request.setVolunteerID(updatedRequest.getVolunteerID());
                    request.setDemandeurID(updatedRequest.getDemandeurID());
                    requestRepository.save(request); // Sauvegarde des modifications
                    return "Request updated successfully!";
                })
                .orElse("Request not found!"); // Si la requete n'existe pas
    }

    @PutMapping("/update/{id}/{state}")
    public String changeState(@PathVariable Long id, @PathVariable String state) {
    	return requestRepository.findById(id).map(request -> {
    		request.setState(state);
    		requestRepository.save(request);
    		return "State changed";
    	})
    	.orElse("Request not found");
    }
    
    @PutMapping("/accept/{id}/{volID}")
    public String acceptVolunteer(@PathVariable Long id, @PathVariable Long volID) {
    	return requestRepository.findById(id).map(request -> {
    		request.setVolunteerID(volID);
    		request.setState("accepted");
    		requestRepository.save(request);
    		return "State changed";
    	})
    	.orElse("Request not found");
    }
    
    @PutMapping("remove/{volunteerID}")
	public String removeAllDemandeur(@PathVariable Long volunteerID) {
		for (Request r : requestRepository.findAll()){
			if (r.getVolunteerID() != null && r.getVolunteerID().equals(volunteerID)) {
				r.setVolunteerID(null);
				requestRepository.save(r);
			}
		};return "Successfully removed volunteer";
	}
    
    
    // Endpoint pour supprimer une demande
    @DeleteMapping("delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        if (requestRepository.existsById(id)) {
        	
        	requestRepository.deleteById(id);
        	return "Request deleted successfully";
        } else {
        	return "Request not found";
        }
    }
    
    @DeleteMapping("delete/demandeur/{demandeurID}")
    	public String deleteAllDemandeur(@PathVariable Long demandeurID) {
    		for (Request r : requestRepository.findAll()){
    			if (r.getDemandeurID().equals(demandeurID)) {
    				requestRepository.delete(r);
    			}
    		};return "Successfully deleted entries";
    	}
    	
   }
