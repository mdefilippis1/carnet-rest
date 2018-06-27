package carnet;
import carnet.Produit;
import java.util.HashMap;

public class Commande {
    private String numero, adresse, statut;
    private Personne client;
    private HashMap<String,Produit> produits;

    public Commande() {}

    public Commande(String num, String adr) {
	this.numero = num;
	this.adresse = adr;
	this.statut = "commande non initialisée";
	this.produits = new HashMap<String,Produit>();
    }

    public String getNumero() {
	return this.numero;
    }

    public void setNumero(String num) {
	this.numero = num;
    }

    public String getAdresse() {
	return this.adresse;
    }

    public void setAdresse(String adr) {
	this.adresse = adr;
    }
    
    public String getStatut() {
    	return this.statut;
    }
    
    public void setStatut(String s) {
    	this.statut = s;
    }
    
    public Personne getClient() {
    	return this.client;
    }
    
    public void setClient(Personne p) {
    	this.client = p;
    }

    public String toString() {
	return this.getNumero() + ":" + this.getAdresse() + ":" + this.getStatut();
    }
    
    public HashMap<String,Produit> getProduits(){
    	return this.produits;
    }
    
    public void ajouterProduit(Produit p,String nom) {
    	this.produits.put(nom, p);
    }
}
