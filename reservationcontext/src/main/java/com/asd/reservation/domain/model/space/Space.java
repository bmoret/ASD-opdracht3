package java.com.asd.reservation.domain.model.space;

import java.com.asd.reservation.domain.model.building.BuildingId;
import java.com.asd.reservation.domain.model.facility.Facility;
import java.util.List;

public class Space {
    private SpaceId spaceId;
    private String spaceName;
    private RoomLocation roomLocation;
    private Size size;
    private PersonCapacity personCapacity;
    private BuildingId buildingId;
    private List<Facility> facilities;

    public Space(SpaceId spaceId, String spaceName, RoomLocation roomLocation, Size size, PersonCapacity personCapacity, BuildingId buildingId, List<Facility> facilities) {
        this.spaceId = spaceId;
        this.spaceName = spaceName;
        this.roomLocation = roomLocation;
        this.size = size;
        this.personCapacity = personCapacity;
        this.buildingId = buildingId;
        this.facilities = facilities;
    }
}
