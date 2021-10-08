package main.java.com.asd.session.domain.model.person;

import java.io.Serializable;
import java.util.UUID;

public class AttendanceId implements Serializable {
    private final UUID id;

    public AttendanceId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
