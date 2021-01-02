package io.github.bluelms.people.core;

public interface EventBus {
    void publish(DomainEvent event);
}
