package db;

import java.util.ArrayList;
import java.util.List;

import modele.Reservation;

public final class DataBase {

	
	private  static final List<Reservation> reservations = new ArrayList<>();
	
	
	public static List<Reservation> getReservations() {
		
		return reservations;
	}
}
