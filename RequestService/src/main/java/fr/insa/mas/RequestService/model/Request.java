package fr.insa.mas.RequestService.model;

public class Request {
    private Long id;
    private String title;
    private String description;
    private String status; // waiting, validated, rejected, chosen, realized
    private Long userId;   // ID de l'utilisateur ayant créé la demande

    // Constructeurs
    public Request() {
    }

    public Request(Long id, String title, String description, String status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // toString pour afficher les informations
    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
