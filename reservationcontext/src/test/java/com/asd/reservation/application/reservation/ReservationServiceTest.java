package java.com.asd.reservation.application.reservation;

import javassist.NotFoundException;
import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.domain.model.space.Space;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    final ReservationService reservationService = new ReservationService(reservationRepository);

    private Space space1;
    private Space space2;
    private ReservationId reservationId;
    private Reservation reservation1Room1;
    private Reservation reservation2Room1;
    private Reservation reservation3Room2;

    @BeforeEach
    void beforeEach() {

        space1 = new Space(null,null,null,null,null,null);
        space2 = new Space(null,null,null,null,null,null);

        reservationId = new ReservationId(UUID.randomUUID());

        reservation1Room1 = new Reservation(reservationId, null, null, space1, null);
        reservation2Room1 = new Reservation(null, null, null, space1, null);
        reservation3Room2 = new Reservation(null, null, null, space2, null);
    }

    @Test
    void testFindsAllReservationsInSpace() {
        List<Reservation> reservationsSpace1 = reservationService.getReservationsBySpace(space1);
        assertEquals(2,reservationsSpace1.size());
        assertEquals(space1,reservationsSpace1.get(0).getSpace());
        assertEquals(space1,reservationsSpace1.get(1).getSpace());
        List<Reservation> reservationsSpace2 = reservationService.getReservationsBySpace(space2);
        assertEquals(1,reservationsSpace2.size());
        assertEquals(space2,reservationsSpace2.get(0).getSpace());
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