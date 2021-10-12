package main.java.com.asd.session.application.session;

import main.java.com.asd.session.domain.model.reservation.SpaceId;
import main.java.com.asd.session.domain.model.session.Session;
import main.java.com.asd.session.domain.model.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void reserveSpaceForSession(SpaceReservationCommand spaceReservationCommand) {
        Session session = sessionRepository.getById(spaceReservationCommand.getSessionId());
        session.reserveSpace(new SpaceId(spaceReservationCommand.getSpaceId()));
    }
}
