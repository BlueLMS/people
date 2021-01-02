package io.github.bluelms.people.core;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

// https://dzone.com/articles/spring-managed-hibernate-event-listeners

@Component
public class HibernateListenersConfigurer {
    Logger logger = LoggerFactory.getLogger(LoggingEventBus.class);

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private JpaEventPublisher eventPublisher;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(eventPublisher);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(eventPublisher);
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(eventPublisher);

    }
}
