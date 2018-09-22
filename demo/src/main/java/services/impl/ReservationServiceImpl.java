package services.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import modele.Reservation;
import services.IReservationService;
import dao.IReservationDao;

public class ReservationServiceImpl implements IReservationService {

	protected IReservationDao reservationDao;
	
	
	public ReservationServiceImpl(IReservationDao dao){
		this.reservationDao=dao;
		
	}
	
	public Reservation  effectuerReservation(String nom, String prenom, String dateDeb,
			String commentaire, String categorie) {
		
		if (nom == null || nom.isEmpty()){
			throw new RuntimeException("nom obligatoire non renseigné");
		}
		
		else if (dateDeb == null || dateDeb.isEmpty()){
			throw new RuntimeException("date obligatoire non renseigné");
		}
		LocalDate dateDebut;
		
		try {
			dateDebut = LocalDate.parse(dateDeb, DateTimeFormatter.ofPattern("d/MM/yyyy"));
		}
		catch (DateTimeParseException e){
			throw new RuntimeException("format de date incorrect",e);
		}
		
		if (commentaire!=null && commentaire.length()>255){
			throw new RuntimeException("commentaire limité à 255 caractères");
		}
		
		
		
		return reservationDao.saveReservation(nom, prenom, dateDebut, commentaire, categorie);

	}

}
