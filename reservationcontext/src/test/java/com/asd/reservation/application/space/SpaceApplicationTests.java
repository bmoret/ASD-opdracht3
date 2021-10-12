package test.java.com.asd.reservation.application.space;

import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.application.space.SpaceService;
import main.java.com.asd.reservation.domain.model.building.Address;
import main.java.com.asd.reservation.domain.model.building.Building;
import main.java.com.asd.reservation.domain.model.space.PersonCapacity;
import main.java.com.asd.reservation.domain.model.space.RoomLocation;
import main.java.com.asd.reservation.domain.model.space.Size;
import main.java.com.asd.reservation.domain.model.space.Space;
import main.java.com.asd.reservation.repository.ReservationRepository;
import main.java.com.asd.reservation.repository.SpaceRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SpaceApplicationTests {

    final SpaceRepository spaceRepository = Mockito.mock(SpaceRepository.class);
    final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    final ReservationService reservationService = new ReservationService(reservationRepository);
    final SpaceService spaceService = new SpaceService(spaceRepository, reservationService);

    @Test
    public void CreateSpace(){

        RoomLocation location = new RoomLocation("Room 1", 1);
        PersonCapacity capacity = new PersonCapacity(25);
        Size size = new Size(40, 40);

        Address address = new Address("Van Lieflandlaan", "19", "1222FD", "Utrecht");

        Building building = new Building("Ziekenhuis ABC", address);

        Space space = spaceService.newSpace(
                "New Space",
                location,
                size,
                capacity,
                building.getId(),
                new ArrayList<>()
        );

        System.out.println(building.getId().id);
    }

    @Test
    public void GetAvailableSpacesForDate(){
        List<Space> spaces = spaceService.getSpaces();
        spaceService.getAvailableSpacesForDate(null);
    }
}