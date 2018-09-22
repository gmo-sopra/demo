import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import modele.Categorie;
import modele.Client;
import modele.Reservation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import services.impl.ReservationServiceImpl;
import dao.IReservationDao;


public class ReservationServiceTU {
	

	@Mock
	IReservationDao reservationDao;
	@InjectMocks
	ReservationServiceImpl reservationService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	 
	/**
	 * attendu: Réservation renvoyée
	 */
	@Test
	public void effectuerReservationCasNominal() {
		
		when(reservationDao.saveReservation(anyString(), anyString(),any(LocalDate.class),anyString(),anyString())).
		then(r -> new Reservation(1,r.getArgument(2),1,1,1,55d,r.getArgument(3),new Client(r.getArgument(0),r.getArgument(1)),new Categorie("simple",55d)));
		
		Reservation result = reservationService.effectuerReservation("Martin", "Paul", "01/01/2017", "formation", "simple");
		
		assertThat("Martin",is(equalTo(result.getClient().getNom())));
		assertThat("Paul",equalTo(result.getClient().getPrenom()));
		assertThat(1,equalTo(result.getNombreNuites()));
		assertThat(1,equalTo(result.getNombrePersonnes()));
		assertThat(1,equalTo(result.getNombrePetitDej()));
		assertThat(LocalDate.of(2017, 1, 1),equalTo(result.getDateDeb()));
		assertThat("formation",equalTo(result.getCommentaire()));
		assertThat("simple",equalTo(result.getCategorie().getType()));
		
	}
	
	/**
	 * attendu : exception nom obligatoire
	 */
	@Test(expected=RuntimeException.class)
	public void effectuerReservationNomObligatoire() {
		
		Reservation result = reservationService.effectuerReservation(null, "Paul", "01/01/2017", "formation", "simple");
			
	}
	
	
	/**
	 * attendu : exception format de date dd/MM/yyyy
	 */
	@Test
	public void effectuerReservationFormatDateIncorrect() {

		try {
			Reservation result = reservationService.effectuerReservation("Martin",
					"Paul", "01-01-2017", "formation", "simple");
			fail();

		} catch (RuntimeException e) {
			assertThat("format de date incorrect", equalTo(e.getMessage()));
		}

	}
	
	/**
	 * attendu : exception format de date dd/MM/yyyy
	 */
	@Test
	public void effectuerReservationTailleMaxChampCommentaire() {

			thrown.expect(RuntimeException.class);
			thrown.expectMessage("commentaire limité à 255 caractères");
			
			Reservation result = reservationService.effectuerReservation("Martin",
					"Paul", "01/01/2017", IntStream.range(1, 257).mapToObj(i -> "a").collect(Collectors.joining("")), "simple");
			

	}
	
	/**
	 * teste appelle bien le DAO
	 * 
	 */
	
	@Test
	public void effectuerReservationPasseDansDao() {
		
		
		Reservation result = reservationService.effectuerReservation("Martin", "Paul", "01/01/2017", "formation", "simple");
		verify(reservationDao).saveReservation(anyString(), anyString(),any(LocalDate.class),anyString(),anyString());
		
	}
	
	
	
	

}
