package main.java.com.asd.session.application.session;

import javassist.NotFoundException;
import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.session.Session;
import main.java.com.asd.session.domain.model.session.SessionRepository;
import main.java.com.asd.session.application.person.PersonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;
    private final PersonService personService;

    public SessionService(SessionRepository sessionRepository, PersonService personService) {
        this.sessionRepository = sessionRepository;
        this.personService = personService;
    }

    //haalt alle sessies op
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    //haalt alle sessions die "personId" bevatten en
    // plaatsvinden na het huidige moment van uitvoeren van deze functie(LocalDateTime.now()).
    public List<Session> getFutureSessionsByPersonId(UUID id) throws NotFoundException {
        PersonId personId = personService.getPersonId(id);
        return getSessions().stream()
                .filter(session -> session.containsPersonId(personId) && session.timeSpanBeginAfterNow())
                .collect(Collectors.toList());
    }
}