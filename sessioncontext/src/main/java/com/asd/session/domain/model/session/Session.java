package main.java.com.asd.session.domain.model.session;

import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.reservation.ReservationId;
import main.java.com.asd.session.port.adapter.external.ExternalSystemHttpAdapter;

import java.time.LocalDateTime;
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

    public void reserveSpace(UUID spaceId) {
        UUID reservationUUID = ExternalSystemHttpAdapter.instance().makeReservation(spaceId, timeSpan.getBegin(), timeSpan.getEnd());
        this.reservationId = new ReservationId(reservationUUID);
    }
}
