package main.java.com.asd.reservation.port.adapter.api.rest.reservation;
import javassist.NotFoundException;
import main.java.com.asd.reservation.application.reservation.ReservationService;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class RservationController {
    private final ReservationService reservationService;

    public RservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping(path = "/{id}/changeDateTime")
    public Boolean changeReservationDateTime(@PathVariable UUID id, @RequestBody ReservationRequest rr) throws NotFoundException {

        return reservationService.changeReservationDateTime(id, rr.startDateTime, rr.endDateTime);
    }
}
