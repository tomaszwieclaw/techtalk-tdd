package com.teaminternational.tddgym.domain.factory;

import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;
import com.teaminternational.tddgym.domain.port.MemberRepository;
import com.teaminternational.tddgym.domain.service.DateTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Component
public class MemberFactory {
    private final MemberRepository memberRepository;
    private final DateTimeService dateTimeService;

    public Member createNew(final SubscribeNewMemberCommand command) {
        final OffsetDateTime now = dateTimeService.currentOffsetDateTime();
        return new Member(
                memberRepository.nextId(),
                command.firstName(),
                command.lastName(),
                command.dob(),
                command.sex(),
                command.juicyDetails(),
                now,
                now
        );
    }
}
