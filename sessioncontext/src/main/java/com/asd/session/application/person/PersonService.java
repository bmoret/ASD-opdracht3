package main.java.com.asd.session.application.person;

import javassist.NotFoundException;
import main.java.com.asd.session.domain.model.person.Person;
import main.java.com.asd.session.domain.model.person.PersonId;
import main.java.com.asd.session.domain.model.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService {
        public PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //checkt of er een "PersonId" met de waarde van "id" bestaat en haalt Person op.
    public Person getPerson(UUID id) throws NotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Person with id '%s', does not exist.", id)));
    }

    //haalt PersonId op
    public PersonId getPersonId(UUID id) throws NotFoundException {
        return getPerson(id).getPersonId();
    }
}