package java.com.asd.reservation.application.reservation;

import javassist.NotFoundException;
import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.repository.ReservationRepository;
import main.java.com.asd.reservation.domain.model.space.SpaceId;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import main.java.com.asd.reservation.domain.model.reservation.ReservationId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    final ReservationService reservationService = new ReservationService(reservationRepository);

    private SpaceId spaceId1;
    private SpaceId spaceId2;
    private ReservationId reservationId;
    private Reservation reservation1Room1;
    private Reservation reservation2Room1;
    private Reservation reservation3Room2;

    @BeforeEach
    void beforeEach() {
        spaceId1 = new SpaceId(UUID.randomUUID());
        spaceId2 = new SpaceId(UUID.randomUUID());

        reservationId = new ReservationId(UUID.randomUUID());

        reservation1Room1 = new Reservation(reservationId, null, null, spaceId1, null);
        reservation2Room1 = new Reservation(null, null, null, spaceId1, null);
        reservation3Room2 = new Reservation(null, null, null, spaceId2, null);
    }

    @Test
    void testFindsAllReservationsInSpace() {
        List<Reservation> reservationsSpace1 = reservationService.getReservationsBySpace(spaceId1);
        assertEquals(2,reservationsSpace1.size());
        assertEquals(spaceId1,reservationsSpace1.get(0).getSpaceId());
        assertEquals(spaceId1,reservationsSpace1.get(1).getSpaceId());
        List<Reservation> reservationsSpace2 = reservationService.getReservationsBySpace(spaceId2);
        assertEquals(1,reservationsSpace2.size());
        assertEquals(spaceId2,reservationsSpace2.get(0).getSpaceId());
    }

    @Test
    void testSavesChangedDateTime() {
        TimeSpan timeSpan = new TimeSpan(LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        try {
            assertTrue(reservationService.changeReservationDateTime(reservationId.getId(), timeSpan.getBegin().toString(), timeSpan.getEnd().toString()));

            Reservation reservation = reservationService.getReservationById(reservationId.getId());

            assertEquals(timeSpan, reservation.getTimeSpan());
        } catch (NotFoundException nfe) {
            fail();
        }
    }
}