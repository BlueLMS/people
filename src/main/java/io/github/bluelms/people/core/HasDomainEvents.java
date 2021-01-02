package io.github.bluelms.people.core;

import java.util.Collection;

public interface HasDomainEvents {
    Collection<DomainEvent> events();
}
