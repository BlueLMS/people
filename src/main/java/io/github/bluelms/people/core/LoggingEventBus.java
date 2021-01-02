package io.github.bluelms.people.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingEventBus implements EventBus {

    Logger logger = LoggerFactory.getLogger(LoggingEventBus.class);

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHED EVENT: " + event.getClass().toString());
    }
}
