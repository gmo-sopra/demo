import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import modele.Reservation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;

import dao.impl.ReservationDaoImpl;
import db.DataBase;


public class ReservationDaoTU {

	
	ReservationDaoImpl reservationDaoImpl = new ReservationDaoImpl();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Rule
	public Stopwatch chrono = new Stopwatch() {};
	
	@Before
	public void viderDatabase(){
		
		DataBase.getReservations().clear();
	}
	
	@Test
	public void saveReservationCasNominal() {
		
		Reservation r = reservationDaoImpl.saveReservation("Martin", "Paul", LocalDate.of(2017, 1, 1), "formation", "simple");
		
	
		//valeur de retour
		assertThat("Martin",is(equalTo(r.getClient().getNom())));
		assertThat("Paul",equalTo(r.getClient().getPrenom()));
		assertThat(1,equalTo(r.getNombreNuites()));
		assertThat(1,equalTo(r.getNombrePersonnes()));
		assertThat(1,equalTo(r.getNombrePetitDej()));
		assertThat(LocalDate.of(2017, 1, 1),equalTo(r.getDateDeb()));
		assertThat("formation",equalTo(r.getCommentaire()));
		assertThat("simple",equalTo(r.getCategorie().getType()));
		
		
		//en "base"
		assertThat(1,equalTo(DataBase.getReservations().size()));
		Reservation result = DataBase.getReservations().get(0);
		assertThat("Martin",is(equalTo(result.getClient().getNom())));
		assertThat("Paul",equalTo(result.getClient().getPrenom()));
		assertThat(1,equalTo(result.getNombreNuites()));
		assertThat(1,equalTo(result.getNombrePersonnes()));
		assertThat(1,equalTo(result.getNombrePetitDej()));
		assertThat(LocalDate.of(2017, 1, 1),equalTo(result.getDateDeb()));
		assertThat("formation",equalTo(result.getCommentaire()));
		assertThat("simple",equalTo(result.getCategorie().getType()));
		
	}
	
	
	@Test
	public void saveReservationPlusDisponible() {
		
		
		reservationDaoImpl.saveReservation("Martin", "Jean", LocalDate.of(2017, 1, 1), "formation", "simple");
		reservationDaoImpl.saveReservation("Doe", "John", LocalDate.of(2017, 1, 1), "formation", "simple");
		reservationDaoImpl.saveReservation("Potter", "Harry", LocalDate.of(2017, 1, 1), "formation", "simple");
		reservationDaoImpl.saveReservation("Dumbledore", "Albus", LocalDate.of(2017, 1, 1), "formation", "simple");
		
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("pas de chambre de cette categorie disponible");
		reservationDaoImpl.saveReservation("Ren", "Kylo", LocalDate.of(2017, 1, 1), "formation", "simple");
	}
	
	
	@Test
	public void saveReservationTempsReponse() throws InterruptedException {
			
		reservationDaoImpl.saveReservation("Martin", "Jean", LocalDate.of(2017, 1, 1), "formation", "simple");
		assertThat(1l,greaterThan(chrono.runtime(TimeUnit.MILLISECONDS)));
	}
}
