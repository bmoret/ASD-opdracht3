package main.java.com.asd.reservation.domain.model.reservation;

import java.io.Serializable;
import java.util.UUID;

public class ReservationId implements Serializable {
    private final UUID id;

    public ReservationId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
