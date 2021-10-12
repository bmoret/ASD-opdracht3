package test.java.com.asd.session.session;

import main.java.com.asd.session.application.person.PersonService;
import main.java.com.asd.session.application.session.SessionService;
import main.java.com.asd.session.domain.model.person.Person;
import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.person.PersonRepository;
import main.java.com.asd.session.domain.model.session.Session;
import main.java.com.asd.session.domain.model.session.SessionId;
import main.java.com.asd.session.domain.model.session.SessionRepository;
import main.java.com.asd.session.domain.model.session.TimeSpan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SessionTest {
    private static final PersonRepository personRepository = mock(PersonRepository.class);

    private static final SessionRepository sessionRepository = mock(SessionRepository.class);

    private final PersonService personService = new PersonService(personRepository);

    private final SessionService sessionService = new SessionService(sessionRepository, personService);

    static PersonId personId1 = new PersonId(UUID.randomUUID());
    static PersonId personId2 = new PersonId(UUID.randomUUID());
    static PersonId personId3 = new PersonId(UUID.randomUUID());
    static PersonId personId4 = new PersonId(UUID.randomUUID());

    @ParameterizedTest
    @MethodSource("createExamples")
    @DisplayName("function gives future sessions by 'personId' in service.")
    void checkSessionReturnInService(UUID personId, Person person, List<Session> sessions, int expectedResults) {
        when(personRepository.findById(personId)).thenReturn(Optional.ofNullable(person));
        when(sessionRepository.findAll()).thenReturn(sessions);
        List<Session> serviceResult = assertDoesNotThrow(() -> sessionService.getFutureSessionsByPersonId(personId));
        assertEquals(expectedResults, serviceResult.size());

        //sout om te presenteren
        System.out.printf("Verwachte aantal resultaten '%s', daadwerkelijke aantal resultaten '%s'%n%n", expectedResults, serviceResult.size());
        System.out.printf("In deze test wordt gezocht naar: %s%n%nGevonden sessies:%n", personId);
        serviceResult.forEach(session -> System.out.println(session.toString()));
        System.out.printf("%nalle sessies:%n");
        sessionService.getSessions().forEach(session -> System.out.println(session.toString()));
    }

    private static Stream<Arguments> createExamples() {
        return Stream.of(
                Arguments.of(
                        personId1.getId(),
                        new Person(personId1, null, null, null, null),
                        //personId1 is in dit voorbeeld te vinden als sessieOwner
                        List.of(
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId1, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId3, List.of(personId4), null)
                        ),
                        1
                ),
                Arguments.of(
                        personId2.getId(),
                        new Person(personId2, null, null, null, null),
                        //personId2 zowel sessieOwner als attendee
                        List.of(
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId1, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId3, List.of(), null)
                        ),
                        2
                ),
                Arguments.of(
                        personId1.getId(),
                        new Person(personId1, null, null, null, null),
                        //komt 4 keer voor maar 2 in het verleden waardoor 2 resultaten
                        List.of(
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId1, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId1, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1)),
                                        personId1, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1)),
                                        personId2, List.of(personId3, personId4, personId1), null)
                        ),
                        2
                ),
                Arguments.of(
                        personId1.getId(),
                        new Person(personId1, null, null, null, null),
                        //komt voor als sessieOwner en attendee maar in beide gevallen ligt de begin datum voor het huidige moment.
                        List.of(
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId1, List.of(personId2, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId1, personId3, personId4), null),
                                new Session(new SessionId(UUID.randomUUID()), null, null, null,
                                        null, new TimeSpan(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(2)),
                                        personId2, List.of(personId3, personId4), null)
                        ),
                        0
                )
        );
    }
}

