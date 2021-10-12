package test.java.com.asd.reservation.application.reservation;

import javassist.NotFoundException;
import main.java.com.asd.reservation.domain.model.account.AccountId;
import main.java.com.asd.reservation.domain.model.reservation.*;
import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.domain.model.building.Address;
import main.java.com.asd.reservation.domain.model.building.Building;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.space.PersonCapacity;
import main.java.com.asd.reservation.domain.model.space.RoomLocation;
import main.java.com.asd.reservation.domain.model.space.Size;
import main.java.com.asd.reservation.domain.model.space.Space;
import main.java.com.asd.reservation.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ReservationApplicationTest {
    private Building building;
    private Building wrongbuilding;
    private Reservation reservation;
    final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    final ReservationService reservationService = new ReservationService(reservationRepository);

    private ReservationId reservationId;
    @Before
    public void setUp(){
        RoomLocation location = new RoomLocation("Room1", 1);
        PersonCapacity capacity = new PersonCapacity(100);
        Size size = new Size(50, 50);
        Address address = new Address("straat", "1", "1234AB", "Stad");
        Building building1 = new Building("Gebouw1", address);
        Building building2 = new Building("Gebouw2", address);
        Building building3 = new Building("Gebouw3", address);
        wrongbuilding = new Building("Gebouw4", address);

        building = building1;
        Space space1 = new Space(
                "New Space",
                location,
                size,
                capacity,
                building1.getId(),
                new ArrayList<>()
        );
        Space space2 = new Space(
                "New Space",
                location,
                size,
                capacity,
                building2.getId(),
                new ArrayList<>()
        );
        Space space3 = new Space(
                "New Space",
                location,
                size,
                capacity,
                building3.getId(),
                new ArrayList<>()
        );
        TimeSpan timeSpan = new TimeSpan(LocalDateTime.now(), LocalDateTime.now());
        Reservation reservation1 = new Reservation(new ReservationId(UUID.randomUUID()), null, timeSpan, space1, new AccountId(UUID.randomUUID()));
        Reservation reservation2 = new Reservation(new ReservationId(UUID.randomUUID()),null, timeSpan, space2, new AccountId(UUID.randomUUID()));
        Reservation reservation3 = new Reservation(new ReservationId(UUID.randomUUID()),null, timeSpan, space3, new AccountId(UUID.randomUUID()));
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);

        reservation = reservation1;
    }

    @Test
    public void testgetReservationByBuildingCorrectSize() throws NotFoundException {
        Assert.assertEquals(reservationService.getReservationsByBuilding(building.getId().id).size(), 1);
    }

    @Test
    public void testgetReservationByBuildingCorrectBuilding() throws NotFoundException {
        Assert.assertNotEquals(reservationService.getReservationsByBuilding(building.getId().id).get(0).getReservationId(), reservation.getReservationId());
    }

    @Test
    public void testgetReservationByBuildingNotExist() throws NotFoundException {
        Assert.assertTrue(reservationService.getReservationsByBuilding(wrongbuilding.getId().id).isEmpty());
    }
}
