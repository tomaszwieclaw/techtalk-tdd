package com.teaminternational.tddgym.infrastructure.event;

import com.teaminternational.tddgym.domain.model.DomainEvent;
import com.teaminternational.tddgym.domain.port.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceAdapter implements EventService {
    private final EventQueue eventQueue;

    @Override
    public void sendEvent(final DomainEvent domainEvent) {
        eventQueue.add(domainEvent);
    }
}
