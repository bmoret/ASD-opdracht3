package main.java.com.asd.session.domain.model.session;

import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.reservation.ReservationId;
import main.java.com.asd.session.domain.model.reservation.SpaceId;
import main.java.com.asd.session.port.adapter.external.ExternalSystemHttpAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session {
    private SessionId sessionId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private TimeSpan timeSpan;
    private PersonId sessionOwner;
    private List<PersonId> attendees;
    private ReservationId reservationId;

    public Session(SessionId sessionId, String name, String description,
                   LocalDateTime createdAt, LocalDateTime modifiedAt, TimeSpan timeSpan, PersonId sessionOwner,
                   List<PersonId> attendees, ReservationId reservationId) {
        this.sessionId = sessionId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.timeSpan = timeSpan;
        this.sessionOwner = sessionOwner;
        this.attendees = attendees;
        this.reservationId = reservationId;
    }

    public void reserveSpace(SpaceId spaceId) {
        UUID reservationUUID = ExternalSystemHttpAdapter.instance().makeReservation(spaceId, timeSpan.getBegin(), timeSpan.getEnd());
        this.reservationId = new ReservationId(reservationUUID);
    }

    public boolean containsPersonId(PersonId id) {
        return this.sessionOwner.equals(id) ||
                this.attendees.contains(id);
    }

    public boolean timeSpanBeginAfterNow() {
        return this.timeSpan.timeSpanBeginAfterNow();
    }

    public PersonId getSessionOwner() {
        return sessionOwner;
    }

    public List<PersonId> getAttendees() {
        return attendees;
    }

    @Override
    public String toString() {
        List<UUID> ids = new ArrayList<>();
        attendees.forEach(personId -> ids.add(personId.getId()));
        return "Session{" +
                "sessionId=" + sessionId +
                ", beginTijd=" + timeSpan.getBegin().getHour() +":"+ timeSpan.getBegin().getMinute() +
                ", sessionOwner=" + sessionOwner.getId() +
                ", attendees=" + ids +
                '}';
    }
}
