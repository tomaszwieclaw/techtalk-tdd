package com.teaminternational.tddgym.infrastructure.persistence;

import com.teaminternational.tddgym.domain.model.Member;
import com.teaminternational.tddgym.domain.port.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberRepositoryAdapter implements MemberRepository {

    @Override
    public void save(final Member member) {

    }
}
