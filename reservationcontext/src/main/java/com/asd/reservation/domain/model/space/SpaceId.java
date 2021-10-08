package java.com.asd.reservation.domain.model.space;

import java.io.Serializable;
import java.util.UUID;

public class SpaceId implements Serializable {
    private final UUID id;

    public SpaceId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
