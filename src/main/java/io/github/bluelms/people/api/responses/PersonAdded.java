package io.github.bluelms.people.api.responses;

public class PersonAdded {
    public String id;

    public PersonAdded(String id) {
        this.id = id;
    }

    public static PersonAdded withId(String id) {
        return new PersonAdded(id);
    }
}
