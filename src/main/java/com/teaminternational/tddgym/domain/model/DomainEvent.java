package com.teaminternational.tddgym.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Data
public class DomainEvent {
    private final Type type;
    private final String content;
    private final OffsetDateTime createdAt;

    public static enum Type {
        NEW_MEMBER_SUBSCRIBED
    }
}
