package dao.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modele.Categorie;
import modele.Client;
import modele.Reservation;
import dao.IReservationDao;
import db.DataBase;

public class ReservationDaoImpl implements IReservationDao {

	final static int NBR_CHAMBRE_CAT1 = 4;
	final static int NBR_CHAMBRE_CAT2 = 4;
	final static int NBR_CHAMBRE_CAT3 = 1;
	
	
	private static enum TYPE_CATEGORIE {
		SIMPLE,
		DOUBLE,
		FAMILIALE;
	}
	
	final Map<TYPE_CATEGORIE,Integer> capacites = new HashMap<>();
	
	
	
	
	public ReservationDaoImpl() {
		capacites.put(TYPE_CATEGORIE.SIMPLE, NBR_CHAMBRE_CAT1);
		capacites.put(TYPE_CATEGORIE.DOUBLE, NBR_CHAMBRE_CAT2);
		capacites.put(TYPE_CATEGORIE.FAMILIALE, NBR_CHAMBRE_CAT3);
	}
	
	
	
	public synchronized Reservation saveReservation(String nom, String prenom,
			LocalDate dateDeb, String commentaire, String categorie) {
		
		int nbChbDispo = capacites.get(TYPE_CATEGORIE.valueOf(categorie.toUpperCase()));
		int currentSize = DataBase.getReservations().size();
		Reservation res=null;
		
		if (DataBase.getReservations().stream().filter(
				r -> categorie.equalsIgnoreCase(r.getCategorie().getType()) && dateDeb.equals(r.getDateDeb())).count() >= nbChbDispo) {
			
			throw new RuntimeException("pas de chambre de cette categorie disponible");
		}
		else {
			DataBase.getReservations().add(res=new Reservation(currentSize+1,dateDeb,1,1,1,7d,commentaire,new Client(nom,prenom),new Categorie(categorie,55d)));
		}
		
		return res;
	}



	public List<Reservation> getReservations() {
		return DataBase.getReservations();
	}
	
	
	

	
	
}
