package com.teaminternational.tddgym.domain.factory;

import com.teaminternational.tddgym.domain.model.DomainEvent;
import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.service.DateTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DomainEventFactory {
    private final DateTimeService dateTimeService;

    public DomainEvent createNewMemberSubscribedEvent(final Member member) {
        return new DomainEvent(
                DomainEvent.Type.NEW_MEMBER_SUBSCRIBED,
                member.id().toString(),
                dateTimeService.currentOffsetDateTime()
        );
    }
}
