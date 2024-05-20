package com.teaminternational.tddgym.infrastructure.event;

import com.teaminternational.tddgym.domain.model.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class EventQueue {
    private final List<DomainEvent> queue = new LinkedList<>();

    public void add(final DomainEvent domainEvent) {
        queue.add(domainEvent);
    }

    public List<DomainEvent> getAll() {
        return Collections.unmodifiableList(queue);
    }
}
