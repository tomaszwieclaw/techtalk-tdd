package com.teaminternational.tddgym.domain.port;

import com.teaminternational.tddgym.domain.model.DomainEvent;

public interface EventService {
    void sendEvent(DomainEvent domainEvent);
}
