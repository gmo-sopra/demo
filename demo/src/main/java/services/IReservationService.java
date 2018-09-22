package services;

import modele.Reservation;

public interface IReservationService {

	
	Reservation effectuerReservation(String nom, String prenom, String dateDeb, String commentaire, String categorie);
}
