package main.java.com.asd.session.application.session;

import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.session.Session;
import main.java.com.asd.session.domain.model.session.SessionId;
import main.java.com.asd.session.domain.model.session.TimeSpan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {
    @Autowired
    private SessionService sessionService;
    private SessionId sessionId;
    private Session session;

    @BeforeEach
    void setUp() {
        //create new session for tests
        sessionId = new SessionId(UUID.randomUUID());
        session = new Session(
                sessionId,
                "name",
                "description",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new PersonId(UUID.randomUUID()),
                new ArrayList<>(),
                null
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void reserveSpaceForSession() {

    }

    @Test
    void reserveSpaceForNotExistingSession() {

    }

    @Test
    void reserveNotExistingSpaceForSession() {

    }

    @Test
    void reserveSpaceForSessionWhenNotAvailable() {

    }

}