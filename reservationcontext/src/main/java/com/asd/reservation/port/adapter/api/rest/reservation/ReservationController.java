package main.java.com.asd.reservation.port.adapter.api.rest.reservation;
import javassist.NotFoundException;
import main.java.com.asd.reservation.application.reservation.ReservationService;
import main.java.com.asd.reservation.domain.model.reservation.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @GetMapping(path = "/{buildingId}")
    public List<Reservation> getReservationsByBuilding(@PathVariable UUID buildingID) throws NotFoundException {
        return reservationService.getReservationsByBuilding(buildingID);
    }
    
    @PutMapping(path = "/{id}/changeDateTime")
    public Boolean changeReservationDateTime(@PathVariable UUID id, @RequestBody ReservationRequest rr) throws NotFoundException {

        return reservationService.changeReservationDateTime(id, rr.startDateTime, rr.endDateTime);
    }
}
