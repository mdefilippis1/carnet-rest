package carnet.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import carnet.CarnetRessource;
import carnet.Personne;
import carnet.Commande;
import carnet.Produit;

@Path("/carnet")
public class Rest {
	CarnetRessource carnet = new CarnetRessource();
	
	@Path("/personnes")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getPersonnes() {
		return carnet.toString();
	}
	
	@Path("/personnes/{adresse}")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getPersonne(@PathParam("adresse") String adresse) {
		return carnet.getPersonne(adresse);
	}
	
	@Path("/adresses/{personne}")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getAdresse(@PathParam("personne") String personne) {
		return carnet.getAdresse(personne);
	}
	
	@Path("/personnes")
	@POST
	@Produces(MediaType.TEXT_PLAIN) 
	public Response postPersonne(@QueryParam("nom") String nom, @QueryParam("adresse") String adresse) {
		Personne personne = new Personne(nom, adresse);
		return carnet.addPersonne(personne);
	}
	
	@Path("/personnes")
	@PUT
	@Produces(MediaType.TEXT_PLAIN) 
	public Response putPersonne(@QueryParam("nom") String nom, @QueryParam("adresse") String adresse) {
		Personne personne = new Personne(nom, adresse);
		return carnet.updateTerm(personne);
	}
	
	@Path("/personnes/{personne}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN) 
	public Response deletePersonne(@PathParam("personne") String personne) {
		return carnet.removeTerm(personne);
	}
	
	@Path("/commandes")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getCommandes() {
		return carnet.getStringCommandes();
	}
	
	@Path("/commandes")
	@POST
	@Produces(MediaType.TEXT_PLAIN) 
	public Response postCommande(@QueryParam("num") String num, @QueryParam("adresse") String adresse) {
		Commande commande = new Commande(num, adresse);
		return carnet.addCommande(commande);
	}
	
	
	@Path("/produits")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getProduits() {
		return carnet.getStringProduits();
	}
	
	@Path("/produits")
	@POST
	@Produces(MediaType.TEXT_PLAIN) 
	public Response postProduit(@QueryParam("nom") String nom, @QueryParam("prix") int prix) {
		Produit produit = new Produit(nom, prix);
		return carnet.addProduit(produit);
	}
	
	@Path("/commandes")
	@PUT
	@Produces(MediaType.TEXT_PLAIN) 
	public Response putProduit(@QueryParam("nomProduit") String produit, @QueryParam("commande") String commande) {
		
		return carnet.updateCommande(commande,produit);
	}
	
	@Path("/details")
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String getProduitsCommande(@QueryParam("commande") String commande) {
		
		return carnet.getProduitsCommande(commande);
	}
	
	
	@Path("/commandes/valider")
	@PUT
	@Produces(MediaType.TEXT_PLAIN) 
	public String putProduit(@QueryParam("commande") String commande) {
		
		return carnet.validerCommande(commande);
	}
	
	@Path("/commandes")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN) 
	public String supprimerCommande(@QueryParam("commande") String commande) {
		
		return carnet.supprimerCommande(commande);
	}

}
