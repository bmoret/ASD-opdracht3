package main.java.com.asd.reservation.application.space;

import main.java.com.asd.reservation.domain.model.building.BuildingId;
import main.java.com.asd.reservation.domain.model.facility.Facility;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import main.java.com.asd.reservation.domain.model.space.*;
import main.java.com.asd.reservation.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SpaceService {

    public SpaceRepository spaceRepository;
    public ReservationService reservationService;

    public SpaceService(SpaceRepository spaceRepository, ReservationService reservationService) {
        this.spaceRepository = spaceRepository;
        this.reservationService = reservationService;
    }

    public Space newSpace(String spaceName,
                          RoomLocation location,
                          Size size,
                          PersonCapacity capacity,
                          BuildingId buildingId,
                          ArrayList<Facility> facilities){
        Space space = new Space(
                spaceName,
                location,
                size,
                capacity,
                buildingId,
                facilities
        );

        spaceRepository.save(space);
        return space;
    }

    public Space getSpace(SpaceId spaceId){
        return spaceRepository.findAll().stream().filter(space -> space.getId() == spaceId).findFirst().get();
    }

    public List<Space> getSpaces() {
        return spaceRepository.findAll();
    }

    public Stream<Space> getAvailableSpacesForDate(TimeSpan timeSpan) {
        return getSpaces().stream().filter(space -> {
            List<Reservation> reservations = reservationService.findBySpace(space.getId());
            return (reservations.stream().noneMatch(reservation -> reservation.timeSpanHasOverlap(timeSpan)));
        });
    }
}
