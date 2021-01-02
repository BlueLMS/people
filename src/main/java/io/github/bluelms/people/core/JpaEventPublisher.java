package io.github.bluelms.people.core;

import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Service;

@Service
public class JpaEventPublisher implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {

    private final EventBus eventBus;

    public JpaEventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        publishEvents(postUpdateEvent.getEntity());
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        publishEvents(postInsertEvent.getEntity());
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        publishEvents(postDeleteEvent.getEntity());
    }

    private void publishEvents(Object entity) {
        if (!(entity instanceof HasDomainEvents)) {
            return;
        }

        System.out.println("publisher " + entity.hashCode() + "(" + entity.getClass().toString() + ")");

        ((HasDomainEvents) entity).events().forEach(eventBus::publish);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
