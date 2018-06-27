package carnet;

public class Produit {
	
	private String nom;
	private int prix;
	
	public Produit() {}
	
	public Produit(String name, int price) {
		this.nom = name;
		this.prix = price;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getPrix() {
		return this.prix;
	}
	
	public void setNom(String name) {
		this.nom = name;
	}
	
	public void setPrix(int price) {
		this.prix = price;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getNom());
		s.append(":");
		s.append(this.getPrix());
		return s.toString();
	}
}
