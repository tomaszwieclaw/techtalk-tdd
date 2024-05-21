package com.teaminternational.tddgym.domain.port;

import com.teaminternational.tddgym.domain.model.Member;

import java.util.UUID;

public interface MemberRepository {
    UUID nextId();

    void save(Member member);
}
