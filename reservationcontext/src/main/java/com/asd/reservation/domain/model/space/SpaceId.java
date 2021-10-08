package java.com.asd.reservation.domain.model.space;

import java.util.UUID;

public class SpaceId {
    private final UUID id;

    public SpaceId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
