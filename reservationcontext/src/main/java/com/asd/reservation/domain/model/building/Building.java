package main.java.com.asd.reservation.domain.model.building;

public class Building {
    private BuildingId buildingId;
    private String name;
    private Address address;

    public Building(String name, Address address) {
        buildingId = new BuildingId();
        this.name = name;
        this.address = address;
    }

    public BuildingId getId() {
        return buildingId;
    }
}