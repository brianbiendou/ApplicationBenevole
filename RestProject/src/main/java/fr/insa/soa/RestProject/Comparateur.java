package fr.insa.soa.RestProject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("comparateur")
public class Comparateur {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Bonjour";
	}
	@GET
	@Path("longueur/{chaine}")
	@Produces(MediaType.TEXT_PLAIN)
	public int getLongueur(@PathParam("chaine")String chaine) {
		return chaine.length();
	}
	@GET
	@Path("longueurDouble")
	@Produces(MediaType.TEXT_PLAIN)
	public int getLongueurDouble(@QueryParam("chaine")String chaine) {
			return chaine.length()*2;
	}

	/*
	@PUT
	@Path("/{idEtudiant")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateEtudiant(@PathParam("idEtudiant") int id) {
		System.out.println("Mise à jour réussie");
	}
	*/
	//Empêche le fonctionnement du service Comparateur

}
