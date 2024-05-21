package com.teaminternational.tddgym.infrastructure.persistence;

import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberRepositoryAdapter implements MemberRepository {
    private final MemberSpringDataRepository memberSpringDataRepository;
    private final MemberEntityMapper memberEntityMapper;

    @Override
    public UUID nextId() {
        return UUID.randomUUID();
    }

    @Override
    public void save(final Member member) {
        Optional.of(member)
                .map(memberEntityMapper::toDatabaseEntity)
                .ifPresent(memberSpringDataRepository::save);
    }
}
