package java.com.asd.reservation.domain.model.building;

public class Building {
    private BuildingId buildingId;
    private String name;
    private Address address;

    public Building(BuildingId buildingId, String name, Address address) {
        this.buildingId = buildingId;
        this.name = name;
        this.address = address;
    }
}
