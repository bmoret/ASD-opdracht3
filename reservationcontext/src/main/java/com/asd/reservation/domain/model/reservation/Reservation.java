package main.java.com.asd.reservation.domain.model.reservation;

import main.java.com.asd.reservation.domain.model.account.AccountId;
import main.java.com.asd.reservation.domain.model.space.SpaceId;

import java.time.LocalDateTime;
import java.util.List;

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

    public Boolean changeDateTime(TimeSpan timeSpan, List<Reservation> reservations) {
        if (timeSpan.getBegin().isAfter(timeSpan.getEnd())) throw new IllegalArgumentException("End time is before start time");
        if (timeSpan.equals(this.timeSpan)) return true;

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(this.reservationId)) continue;

            TimeSpan otherTimeSpan = reservation.getTimeSpan();
            if (timeSpan.hasOverlap(otherTimeSpan)) return false;
        }
        this.timeSpan = timeSpan;
        return true;
    }

    public ReservationId getReservationId() {
        return this.reservationId;
    }

    public TimeSpan getTimeSpan() {
        return this.timeSpan;
    }

    public SpaceId getSpaceId() {
        return this.spaceId;
    }

    public boolean timeSpanHasOverlap(TimeSpan otherTimeSpan) {
        return timeSpan.hasOverlap(otherTimeSpan);
    }
}
