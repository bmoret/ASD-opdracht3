package java.com.asd.account.domain.model.details;

import java.io.Serializable;
import java.util.UUID;

public class PersonDetailsId implements Serializable {
    private final UUID id;

    public PersonDetailsId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
