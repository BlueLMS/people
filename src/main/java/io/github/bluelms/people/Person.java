package io.github.bluelms.people;

import io.github.bluelms.people.core.DomainEvent;
import io.github.bluelms.people.core.HasDomainEvents;
import io.github.bluelms.people.events.PersonWasAdded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Person implements HasDomainEvents {

    @Id
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Transient
    private Collection<DomainEvent> events = new ArrayList<>();

    private Person() {
    }

    private Person(String fullName, String email) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    private void recordThat(DomainEvent event) {
        events.add(event);
        System.out.println("ADDED EVENT");
    }

    public Collection<DomainEvent> events() {
        return events;
    }

    public static Person create(String fullName, String email) {
        Person person = new Person(fullName, email);

        person.recordThat(PersonWasAdded.withId(person.id()));
        System.out.println("entity " + person.hashCode());

        return person;
    }
}
