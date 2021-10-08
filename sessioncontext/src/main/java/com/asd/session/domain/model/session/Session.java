package main.java.com.asd.session.domain.model.session;

import main.java.com.asd.session.domain.model.person.PersonId;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private SessionId sessionId;
    private TimeSpan timeSpan;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private PersonId sessionOwner;
    private List<PersonId> attendees;
}
