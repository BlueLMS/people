package io.github.bluelms.people.api;

import io.github.bluelms.people.PeopleRepository;
import io.github.bluelms.people.Person;
import io.github.bluelms.people.api.requests.AddPerson;
import io.github.bluelms.people.api.responses.PersonAdded;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleRepository peopleRepository;

    public PeopleController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @PostMapping("/")
    public ResponseEntity<PersonAdded> create(@RequestBody AddPerson request) {
        Person person = Person.create(request.fullName, request.email);

        peopleRepository.save(person);

        System.out.println("controller " + person.hashCode());

        return ResponseEntity.ok(PersonAdded.withId(person.id()));
    }
}
