package main.java.com.asd.session.port.adapter.api.rest.reservation;

import main.java.com.asd.session.application.session.SessionService;
import main.java.com.asd.session.application.session.SpaceReservationCommand;
import main.java.com.asd.session.port.adapter.api.rest.reservation.dto.request.SessionSpaceRequest;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SessionController {
    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PatchMapping(path = "/session/{sessionId}/space")
    public void reserveSpaceForSession(
            @PathVariable("sessionId") UUID sessionId, @RequestBody SessionSpaceRequest request
    ) {
        SpaceReservationCommand reservationCommand = new SpaceReservationCommand(
                sessionId,
                request.spaceId
        );
        sessionService.reserveSpaceForSession(reservationCommand);
    }
}
