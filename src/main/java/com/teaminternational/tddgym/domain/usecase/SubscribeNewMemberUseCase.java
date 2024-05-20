package com.teaminternational.tddgym.domain.usecase;

import com.teaminternational.tddgym.domain.model.MemberDTO;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;

public interface SubscribeNewMemberUseCase {

    MemberDTO execute(SubscribeNewMemberCommand command);
}
