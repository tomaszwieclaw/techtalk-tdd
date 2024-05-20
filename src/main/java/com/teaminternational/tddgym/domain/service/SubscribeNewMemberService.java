package com.teaminternational.tddgym.domain.service;

import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.model.MemberDTO;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;
import com.teaminternational.tddgym.domain.usecase.SubscribeNewMemberUseCase;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class SubscribeNewMemberService implements SubscribeNewMemberUseCase {

    @Override
    public MemberDTO execute(final SubscribeNewMemberCommand command) {
        return new Member(
                UUID.randomUUID(),
                command.firstName(),
                command.lastName(),
                command.dob(),
                command.sex(),
                command.juicyDetails(),
                OffsetDateTime.now(),
                OffsetDateTime.now()
        ).toDTO();
    }
}
