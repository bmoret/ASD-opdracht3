package main.java.com.asd.session.domain.model.person;

import java.io.Serializable;
import java.util.UUID;

public class PersonId implements Serializable {
    private final UUID id;

    public PersonId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
