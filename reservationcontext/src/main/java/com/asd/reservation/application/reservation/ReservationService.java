package main.java.com.asd.reservation.application.reservation;

import javassist.NotFoundException;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import main.java.com.asd.reservation.domain.model.reservation.TimeSpan;
import main.java.com.asd.reservation.domain.model.space.Space;
import main.java.com.asd.reservation.domain.model.space.SpaceId;
import main.java.com.asd.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation getReservationById(UUID id) throws NotFoundException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Reservation with id: "+id));
    }
    
    public List<Reservation> getReservationsByBuilding(UUID buildingId) throws NotFoundException {
        List<Reservation> reservations = reservationRepository.findAll(id);
        List<Reservation> reservationsByBuilding = new ArrayList<>();
        for(Reservation reservation : reservations){
            Space space = reservation.getSpace();
            UUID buildingIdReservation = space.getBuildingId();
            if(buildingId == buildingIdReservation){
                reservationsByBuilding.add(reservation);
            }
        }
        return reservationsByBuilding;
    }
    
    public List<Reservation> getReservationsBySpace(Space space) {
        return reservationRepository.findAll().stream().filter(reservation -> reservation.getSpace().equals(space)).toList();
    }

    public Boolean changeReservationDateTime(UUID id, String startDateTime, String endDateTime) throws NotFoundException {
        Reservation reservation = getReservationById(id);
        List<Reservation> reservations = getReservationsBySpace(reservation.getSpace());

        Boolean changed = reservation.changeDateTime(new TimeSpan(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime)), reservations);
        reservationRepository.save(reservation);
        return changed;
    }

    public List<Reservation> allReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findBySpace(SpaceId id) {
        return allReservations().stream().filter(reservation -> reservation.getSpaceId() == id).toList();
    }
}
