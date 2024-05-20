package com.teaminternational.tddgym.domain.service;

import com.teaminternational.tddgym.domain.model.MemberDTO;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;
import com.teaminternational.tddgym.domain.usecase.SubscribeNewMemberUseCase;
import org.springframework.stereotype.Service;

@Service
public class SubscribeNewMemberService implements SubscribeNewMemberUseCase {

    @Override
    public MemberDTO execute(final SubscribeNewMemberCommand command) {
        return null;
    }
}
