package fr.insa.mas.DemandeurService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "demandeur") 
public class Demandeur {
	@Id
    private Long id;
	
    private String name;
    private String email;


    // Constructeurs
    public Demandeur() {
    }

    /*public Demandeur(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }*/
    
    public Demandeur(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString pour afficher les informations
    @Override
    public String toString() {
        return "Demandeur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}