package java.com.asd.reservation.application.reservation;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.com.asd.reservation.domain.model.reservation.Reservation;
import java.com.asd.reservation.domain.model.reservation.ReservationRepository;
import java.com.asd.reservation.domain.model.reservation.TimeSpan;
import java.com.asd.reservation.domain.model.space.SpaceId;
import java.time.LocalDateTime;
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

    public List<Reservation> getReservationsBySpace(SpaceId spaceId) {
        return reservationRepository.findBySpace(spaceId);
    }

    public Boolean changeReservationDateTime(UUID id, String startDateTime, String endDateTime) throws NotFoundException {
        Reservation reservation = getReservationById(id);
        List<Reservation> reservations = getReservationsBySpace(reservation.getSpaceId());

        Boolean changed = reservation.changeDateTime(new TimeSpan(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime)), reservations);
        reservationRepository.save(reservation);
        return changed;
    }
}
