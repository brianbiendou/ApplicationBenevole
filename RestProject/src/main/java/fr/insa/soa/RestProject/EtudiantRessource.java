package fr.insa.soa.RestProject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("etudiant")
public class EtudiantRessource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant getEtudiant(@Context UriInfo uriInfo) {
		Etudiant etudiant=new Etudiant();
		etudiant.setId(01);
		etudiant.setNom("N");
		etudiant.setPrenom("G");
		etudiant.addLink(getUriforSelf(uriInfo,etudiant), "self", "GET");
		etudiant.addLink(getUriforStage(uriInfo), "Stage", "GET");
		return etudiant;
	}	
		
		
	private String getUriforStage(UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder() // http://localhost:8080/RestProkect/webapi
			.path(StageRessource.class) ///stage
			.build()
			.toString();
		return url;
		
	}
		
	
	private String getUriforSelf(UriInfo uriInfo, Etudiant etudiant) {
		String url=uriInfo.getBaseUriBuilder() // http://localhost:8080/RestProkect/webapi
			.path(EtudiantRessource.class) // /etudiant
			.path(Long.toString(etudiant.getId())) ///1
			.build()
			.toString();
		return url;
	
	}	
		
	@GET
	@Path("/{idEtudiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant getEtudiant(@PathParam("idEtudiant") int id) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom("A");
		etudiant.setPrenom("B");
		return etudiant;
	}
	
	@POST
	@Path("/{idEtudiant}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEtudiant(Etudiant etudNouv) {
		System.out.println("Ajout de l'étudiant "+ etudNouv.getPrenom() +" réussie");
		System.out.println("Son binom est "+ etudNouv.getBinome().getPrenom()+" "+ etudNouv.getBinome().getNom());
	}
}
