package main.java.com.asd.session.port.adapter.api.rest.session;

import javassist.NotFoundException;
import main.java.com.asd.session.application.session.SessionService;
import main.java.com.asd.session.domain.model.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}/future")
    public List<Session> getFutureSessionsByPerson(@PathVariable UUID id) throws NotFoundException {
        return service.getFutureSessionsByPersonId(id);
    }
}