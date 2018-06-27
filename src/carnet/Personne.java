package carnet;

public class Personne {
    private String nom, adresse;

    public Personne() {}

    public Personne(String name, String adr) {
	this.nom = name;
	this.adresse = adr;
    }

    public String getNom() {
	return this.nom;
    }

    public void setNom(String name) {
	this.nom = name;
    }

    public String getAdresse() {
	return this.adresse;
    }

    public void setAdresse(String adr) {
	this.adresse = adr;
    }

    public String toString() {
	return this.nom + " : " + this.adresse;
    }
}
