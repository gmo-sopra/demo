package modele;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {

	Integer numero;
	LocalDate dateDeb;
	Integer nombreNuites;
	Integer nombrePetitDej;
	Integer nombrePersonnes;
	Double prixPetitDej;
	String commentaire;
	
	Client client;
	
	Categorie categorie;
	
	
	
	

	

	public Reservation(Integer numero, LocalDate dateDeb, Integer nombreNuites,
			Integer nombrePetitDej, Integer nombrePersonnes,
			Double prixPetitDej, String commentaire, Client client,
			Categorie categorie) {
		super();
		this.numero = numero;
		this.dateDeb = dateDeb;
		this.nombreNuites = nombreNuites;
		this.nombrePetitDej = nombrePetitDej;
		this.nombrePersonnes = nombrePersonnes;
		this.prixPetitDej = prixPetitDej;
		this.commentaire = commentaire;
		this.client = client;
		this.categorie = categorie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public LocalDate getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(LocalDate dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Integer getNombreNuites() {
		return nombreNuites;
	}

	public void setNombreNuites(Integer nombreNuites) {
		this.nombreNuites = nombreNuites;
	}

	public Integer getNombrePetitDej() {
		return nombrePetitDej;
	}

	public void setNombrePetitDej(Integer nombrePetitDej) {
		this.nombrePetitDej = nombrePetitDej;
	}

	public Integer getNombrePersonnes() {
		return nombrePersonnes;
	}

	public void setNombrePersonnes(Integer nombrePersonnes) {
		this.nombrePersonnes = nombrePersonnes;
	}

	public Double getPrixPetitDej() {
		return prixPetitDej;
	}

	public void setPrixPetitDej(Double prixPetitDej) {
		this.prixPetitDej = prixPetitDej;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
}
