package fr.insa.soa.RestProject;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core. Response;
public class ClientRest {
public static void main(String[] args) {
	//Instanciation de Client, necessaire pour l'exécution des requêtes et la consommation des réponses.
	Client client=ClientBuilder.newClient();
	//appel du service Rest, invocation de la méthode get correspondant à L'URL
	Response response = client.target("http://localhost:8080/RestProject/webapi/comparateur/longueur/Toulouse").request().get();
	//lecture de la réponse récupérée
	System.out.println(response.readEntity(String.class));
	}
}