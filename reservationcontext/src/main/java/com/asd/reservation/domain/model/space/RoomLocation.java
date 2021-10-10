package main.java.com.asd.reservation.domain.model.space;

public class RoomLocation {
    private final String roomNumber;
    private final int floorNumber;

    public RoomLocation(String roomNumber, int floorNumber) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
