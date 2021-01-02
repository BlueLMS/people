package io.github.bluelms.people.events;

import io.github.bluelms.people.core.DomainEvent;

public class PersonWasAdded implements DomainEvent {
    private String id;

    private PersonWasAdded(String id) {
        this.id = id;
    }

    public static PersonWasAdded withId(String id) {
        return new PersonWasAdded(id);
    }

    public String id() {
        return id;
    }
}
