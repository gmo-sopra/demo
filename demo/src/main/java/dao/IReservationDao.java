package dao;

import java.time.LocalDate;

import modele.Reservation;

public interface IReservationDao {
	
	Reservation saveReservation(String nom, String prenom, LocalDate dateDeb, String commentaire, String categorie);

}
