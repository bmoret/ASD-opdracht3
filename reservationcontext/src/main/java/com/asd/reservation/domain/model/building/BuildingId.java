package main.java.com.asd.reservation.domain.model.building;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class BuildingId implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    public BuildingId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
