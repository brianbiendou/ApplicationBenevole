package fr.insa.mas.RequestService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "request") 
public class Request {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour auto-incrémenter l'ID
    private Long id;
    private String description;
    private String state; // waiting, validated, rejected, chosen, realized
    private Long demandeurID;   // ID de l'utilisateur ayant créé la demande
    private Long volunteerID;

    // Constructeurs
    public Request() {
    }

    public Request(Long id, String description, String state, Long demandeurID, Long volunteerID) {
        this.id = id;
        this.description = description;
        this.state = state;
        this.demandeurID = demandeurID;
        this.volunteerID = volunteerID;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getDemandeurID() {
    	return this.demandeurID;
    }
    
    public void setDemandeurID(Long id) {
    	this.demandeurID = id;
    }

    public Long getVolunteerID() {
    	return this.volunteerID;
    }
    
    public void setVolunteerID(Long id) {
    	this.volunteerID = id;
    }
    
    // toString pour afficher les informations
    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", demandeurID=" + demandeurID +
                ", volunteerID=" + volunteerID +
                '}';
    }
}
