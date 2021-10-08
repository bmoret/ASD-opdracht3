package java.com.asd.reservation.domain.model.reservation;

import main.java.com.asd.session.domain.model.session.TimeSpan;

import java.com.asd.reservation.domain.model.account.AccountId;
import java.com.asd.reservation.domain.model.space.SpaceId;

public class Reservation {
    private ReservationId reservationId;
    private ReservationStatus reservationStatus;
    private TimeSpan timeSpan;
    private SpaceId spaceId;
    private AccountId accountId;

    public Reservation(ReservationId reservationId, ReservationStatus reservationStatus, TimeSpan timeSpan, SpaceId spaceId, AccountId accountId) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
        this.timeSpan = timeSpan;
        this.spaceId = spaceId;
        this.accountId = accountId;
    }
}
