package main.java.com.asd.session.port.adapter.api.rest.reservation;

import main.java.com.asd.session.application.spacereservation.SpaceReservationService;
import main.java.com.asd.session.port.adapter.api.rest.reservation.dto.request.SessionSpaceRequest;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class ReservationController {
    private SpaceReservationService spaceReservationService;

    public ReservationController(SpaceReservationService spaceReservationService) {
        this.spaceReservationService = spaceReservationService;
    }

    @PatchMapping(path = "/session/{sessionId}/space")
    public void reserveSpaceForSession(
            @PathVariable("sessionId") UUID sessionId, @RequestBody SessionSpaceRequest request
    ) throws IOException {
        spaceReservationService.reserveSpaceForSession(sessionId, request.spaceId);
    }
}
