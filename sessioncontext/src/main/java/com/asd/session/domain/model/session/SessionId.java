package main.java.com.asd.session.domain.model.session;

import java.io.Serializable;
import java.util.UUID;

public class SessionId implements Serializable {
    private final UUID id;

    public SessionId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
