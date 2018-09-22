package modele;

public class Categorie {

	String type;
	Double prix;
	
	
	
	
	public Categorie(String type, Double prix) {
		super();
		this.type = type;
		this.prix = prix;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	
}
