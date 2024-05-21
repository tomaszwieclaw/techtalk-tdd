package com.teaminternational.tddgym.domain.service;

import com.teaminternational.tddgym.domain.factory.DomainEventFactory;
import com.teaminternational.tddgym.domain.factory.MemberFactory;
import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.model.MemberDTO;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;
import com.teaminternational.tddgym.domain.port.EventService;
import com.teaminternational.tddgym.domain.port.MemberRepository;
import com.teaminternational.tddgym.domain.usecase.SubscribeNewMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscribeNewMemberService implements SubscribeNewMemberUseCase {
    private final MemberFactory memberFactory;
    private final MemberRepository memberRepository;
    private final EventService eventService;
    private final DomainEventFactory domainEventFactory;

    @Override
    public MemberDTO execute(final SubscribeNewMemberCommand command) {
        final Member member = memberFactory.createNew(command);
        memberRepository.save(member);
        eventService.sendEvent(domainEventFactory.createNewMemberSubscribedEvent(member));
        return member.toDTO();
    }
}
