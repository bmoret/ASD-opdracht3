package main.java.com.asd.session.application.spacereservation;

import main.java.com.asd.session.domain.model.session.Session;
import main.java.com.asd.session.domain.model.session.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SpaceReservationService {
    private SessionRepository sessionRepository;

    public SpaceReservationService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void reserveSpaceForSession(UUID sessionId, UUID spaceId) {
        Session session = sessionRepository.getById(sessionId);
        session.reserveSpace(spaceId);
    }
}
