package java.com.asd.reservation.domain.model.reservation;

import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private List<Reservation> otherReservations = new ArrayList<>();
    private TimeSpan timeSpan;

    @BeforeEach
    void beforeEach() {
        timeSpan = new TimeSpan(LocalDateTime.now(), LocalDateTime.now().plusHours(3));

        Reservation reservation = new Reservation(
                null,
                null,
                timeSpan,
                null,
                null);

        otherReservations.add(reservation);
    }

    @ParameterizedTest
    @MethodSource("timeSpanCases")
    void timeSpanTests(Long beginChange, Long endChange, Boolean result) {
        TimeSpan newTimeSpan = new TimeSpan(timeSpan.getBegin().plusHours(beginChange), timeSpan.getEnd().plusHours(endChange));
        Reservation reservation = new Reservation(
                null,
                null,
                null,
                null,
                null);
        assertTrue(reservation.changeDateTime(newTimeSpan, otherReservations));
    }

    private static Stream<Arguments> timeSpanCases() {
        return Stream.of(
                Arguments.of(4,4,true),     //after
                Arguments.of(3,3,true),     //just after
                Arguments.of(-4,-4,true),   //before
                Arguments.of(-3,-3,true),   //just before
                Arguments.of(-1,-1,true),   //end inside
                Arguments.of(1,1,true),     //start inside
                Arguments.of(1,-1,true),    //full inside
                Arguments.of(-1,1,true)     //full outside
                );
    }
}