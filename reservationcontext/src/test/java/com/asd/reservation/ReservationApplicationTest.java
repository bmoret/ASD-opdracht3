import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.building.Address;
import main.java.com.asd.reservation.domain.model.building.Building;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import main.java.com.asd.reservation.domain.model.space.PersonCapacity;
import main.java.com.asd.reservation.domain.model.space.RoomLocation;
import main.java.com.asd.reservation.domain.model.space.Size;
import main.java.com.asd.reservation.domain.model.space.Space;

public class ReservationApplicationTest {
   private UUID buildingId;
   private UUID reservationId;
    @Before
    public void setUp(){
        RoomLocation location = new RoomLocation("Room1", 1);
        PersonCapacity capacity = new PersonCapacity(100);
        Size size = new Size(50, 50);
        Address address = new Address("straat", "1", "1234AB", "Stad");
        Building building1 = new Building("Gebouw1", address);
        Building building2 = new Building("Gebouw1", address);
        Building building3 = new Building("Gebouw1", address);

        buildingId = building.getId();
        Space space1 = new space(
                "New Space",
                location,
                size,
                capacity,
                building1.getId(),
                new ArrayList<>()
        );
        Space space2 = new space(
                "New Space",
                location,
                size,
                capacity,
                building2.getId(),
                new ArrayList<>()
        );
        Space space3 = new space(
                "New Space",
                location,
                size,
                capacity,
                building3.getId(),
                new ArrayList<>()
        );
        reservationStatus reservationStatus = new ReservationSTatus();
        TimeSpan timeSpan = new TimeSpan();
        Reservation reservation1 = new Reservation(reservationStatus, timeSpan, space1, 1);
        Reservation reservation2 = new Reservation(reservationStatus, timeSpan, space2, 1);
        Reservation reservation3 = new Reservation(reservationStatus, timeSpan, space3, 1);

        reservationId = reservation1.getId();
    }

    @Test
    public void testgetReservationByBuildingCorrectSize() {
        Assert.assertEquals(reservationService.getReservationsByBuilding(buildingId).size(), 1);
    }

    @Test
    public void testgetReservationByBuildingCorrectBuilding() {
        Assert.assertNotEquals(reservationService.getReservationsByBuilding(1)[0].getId(), reservationId);
    }

    @Test
    public void testgetReservationByBuildingNotExist() {
        Assert.assertEquals(reservationService.getReservationsByBuilding(new UUID(5)), new List<Reservation>());
    }
}
