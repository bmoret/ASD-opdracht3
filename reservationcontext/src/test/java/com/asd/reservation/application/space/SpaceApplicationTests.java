package test.java.com.asd.reservation.application.space;

import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.application.space.SpaceService;
import main.java.com.asd.reservation.domain.model.account.AccountId;
import main.java.com.asd.reservation.domain.model.building.Address;
import main.java.com.asd.reservation.domain.model.building.Building;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import main.java.com.asd.reservation.domain.model.space.PersonCapacity;
import main.java.com.asd.reservation.domain.model.space.RoomLocation;
import main.java.com.asd.reservation.domain.model.space.Size;
import main.java.com.asd.reservation.domain.model.space.Space;
import main.java.com.asd.reservation.repository.ReservationRepository;
import main.java.com.asd.reservation.repository.SpaceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SpaceApplicationTests {

    final SpaceRepository spaceRepository = Mockito.mock(SpaceRepository.class);
    final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    final ReservationService reservationService = new ReservationService(reservationRepository);
    final ReservationService mockResService = Mockito.mock(ReservationService.class);
    final SpaceService spaceService = new SpaceService(spaceRepository, mockResService);

    public Space testSpace;
    public AccountId accountId;

    @BeforeAll
    public void Setup(){

        //Setup space
        RoomLocation location = new RoomLocation("Room 1", 1);
        PersonCapacity capacity = new PersonCapacity(25);
        Size size = new Size(40, 40);
        Address address = new Address("Van Lieflandlaan", "19", "1222FD", "Utrecht");
        Building building = new Building("Ziekenhuis ABC", address);
        AccountId accountId = new AccountId(UUID.randomUUID());
        testSpace = spaceService.newSpace(
                "New Space",
                location,
                size,
                capacity,
                building.getId(),
                new ArrayList<>()
        );

        //Creeer lijst
        List<Space> spaces = Collections.singletonList(testSpace);

        //Setup mock repo
        Mockito.when(spaceRepository.findAll()).thenReturn(spaces);
    }

    @Test
    public void RuimteIsVrij(){

        //Reservering van gister tot vandaag
        Reservation reservation = reservationService.newReservation(
                new TimeSpan(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1)),
                testSpace,
                accountId);

        //Creeer lijst
        List<Reservation> reservations = Collections.singletonList(reservation);

        //Mock method
        Mockito.when(mockResService.getReservationsBySpace(testSpace)).thenReturn(reservations);

        //Test Method
        List<Space> availableSpacesForDate = spaceService.getAvailableSpacesForDate(new TimeSpan(LocalDateTime.now(), LocalDateTime.now().plusHours(4)));

        assertEquals(availableSpacesForDate.size(), 0);

    }

    @Test
    public void RuimteIsNietVrij(){

        //Reservering van vandaag tot morgen
        Reservation reservation = reservationService.newReservation(
                new TimeSpan(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                testSpace,
                accountId);

        //Creeer lijst
        List<Reservation> reservations = Collections.singletonList(reservation);

        //Mock method
        Mockito.when(mockResService.getReservationsBySpace(testSpace)).thenReturn(reservations);

        //Test Method
        List<Space> availableSpacesForDate = spaceService.getAvailableSpacesForDate(new TimeSpan(LocalDateTime.now(), LocalDateTime.now().plusHours(4)));

        assertEquals(availableSpacesForDate.size(), 0);
    }
}