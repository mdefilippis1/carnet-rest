package carnet;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;

import javax.ws.rs.core.Response;

import carnet.Personne;
import carnet.Commande;
import carnet.Produit;

public class CarnetRessource
{
    private static HashMap<String,String> listeDePersonnes = new HashMap<String,String>();
    private static HashMap<String,String> listeAdresses = new HashMap<String,String>();
    public static HashMap<String,Commande> listeCommandes = new HashMap<String,Commande>();
    public static HashMap<String,Produit> listeProduits = new HashMap<String,Produit>();
    
    public String getAdresse(String nom) {
    	return CarnetRessource.listeAdresses.get(nom);
    }
    
    public String getStringCommandes() {
    	StringBuilder sb = new StringBuilder();
    	for (Commande c : listeCommandes.values()) {
    		sb.append(c.getNumero());
    		sb.append(":");
    		sb.append(c.getAdresse());
    		sb.append(":");
    		sb.append(c.getStatut());
    		sb.append("\n");
    	}
    	return sb.toString();
    }
    
    
    public String getStringProduits() {
    	StringBuilder sb = new StringBuilder();
    	for (Produit p : listeProduits.values()) {
    		sb.append(p.getNom());
    		sb.append(":");
    		sb.append(p.getPrix());
    		sb.append("\n");
    	}
    	return sb.toString();
    	 
    }

    public String getPersonne(String adresse) {
	return CarnetRessource.listeDePersonnes.get(adresse);
    }
    
    public String toString() {
	String carnet;
	if(CarnetRessource.listeDePersonnes.size() == 0)
	    carnet = "Liste vide";
        else {
	    carnet = "";
	    for(Iterator i=CarnetRessource.listeAdresses.keySet().iterator();i.hasNext();){
		String nom = (String)i.next();
		carnet += nom + " : " + CarnetRessource.listeAdresses.get(nom);
		if(i.hasNext())
		    carnet += "\n";
	    }
	}
	return carnet;
    }
    
    public Response addPersonne(Personne personne) {
    	if (personne.getNom() != null && personne.getAdresse() != null) {
    		CarnetRessource.listeAdresses.put(personne.getNom(), personne.getAdresse());
    		CarnetRessource.listeDePersonnes.put(personne.getAdresse(), personne.getNom());
    		return Response.status(200).entity(personne.toString()).build();
    	}
    	else {
    		return Response.status(200).entity("Parametre nom ou adresse non corrects"+personne.getNom()+" "+personne.getAdresse()).build();
    	}
	
	
    }
    
    public Response addCommande(Commande commande) {
    	if (! CarnetRessource.listeCommandes.containsKey(commande.getNumero())) {
    		CarnetRessource.listeCommandes.put(commande.getNumero(),commande);
    		return Response.status(200).entity(commande.toString()).build();
    	}
    	else {
    		return Response.status(200).entity("Commande deja presente").build();
    	}
    }
    
    public Response addProduit(Produit produit) {
    	CarnetRessource.listeProduits.put(produit.getNom(), produit);
    	return Response.status(200).entity(produit.toString()).build();
    }

    public Response updateTerm(Personne personne) {
	String ancienBureau = this.getAdresse(personne.getNom());
	CarnetRessource.listeAdresses.remove(personne.getNom());
	CarnetRessource.listeAdresses.put(personne.getNom(), personne.getAdresse());
	CarnetRessource.listeDePersonnes.remove(ancienBureau);
	CarnetRessource.listeDePersonnes.put(personne.getAdresse(), personne.getNom());
	return Response.status(200).entity(personne.toString()).build();
    }

    public Response removeTerm(String nom) {
	String adresse = CarnetRessource.listeAdresses.get(nom);
	CarnetRessource.listeAdresses.remove(nom);
	CarnetRessource.listeDePersonnes.remove(adresse);
	return Response.status(200).entity(nom).build();
    }
    
    public Response updateCommande(String commande, String produit) {
    	
    	Commande c = CarnetRessource.listeCommandes.get(commande);
    	CarnetRessource.listeCommandes.remove(commande);
    	Produit p = CarnetRessource.listeProduits.get(produit);
    	c.ajouterProduit(p,p.getNom());
    	CarnetRessource.listeCommandes.put(commande, c);
    	return Response.status(200).entity(commande).build();
    }
    
    public String getProduitsCommande(String commande) {
    	StringBuilder sb = new StringBuilder();
    	Commande c = CarnetRessource.listeCommandes.get(commande);
    	
    	HashMap<String,Produit> liste = c.getProduits();
    	for (Produit p : liste.values()) {
    		sb.append(p.getNom());
    		sb.append(":");
    		sb.append(p.getPrix());
    		sb.append("\n");
    	}
    	return sb.toString();
    	
    }
    
    public String validerCommande(String commande) {
    	Commande c = CarnetRessource.listeCommandes.get(commande);
    	CarnetRessource.listeCommandes.remove(commande);
    	c.setStatut("validee");
    	CarnetRessource.listeCommandes.put(commande, c);
    	return "commande "+commande+" validee";
    }
    
    public String supprimerCommande(String commande) {
    	CarnetRessource.listeCommandes.remove(commande);
    	return "Commande "+commande+" supprimee";
    }
    
}
