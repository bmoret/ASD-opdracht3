package main.java.com.asd.reservation.domain.model.space;

import main.java.com.asd.reservation.domain.model.building.BuildingId;
import main.java.com.asd.reservation.domain.model.facility.Facility;
import java.util.List;
import java.util.UUID;

public class Space {

    private SpaceId spaceId;
    private String spaceName;
    private RoomLocation roomLocation;
    private Size size;
    private PersonCapacity personCapacity;
    private BuildingId buildingId;
    private List<Facility> facilities;



    public Space(String spaceName, RoomLocation roomLocation, Size size, PersonCapacity personCapacity, BuildingId buildingId, List<Facility> facilities) {
        this.spaceId = new SpaceId(UUID.randomUUID());
        this.spaceName = spaceName;
        this.roomLocation = roomLocation;
        this.size = size;
        this.personCapacity = personCapacity;
        this.buildingId = buildingId;
        this.facilities = facilities;
    }

    public SpaceId getId() {
        return spaceId;
    }
    public BuildingId getBuildingId() {
        return buildingId;
    }
}

