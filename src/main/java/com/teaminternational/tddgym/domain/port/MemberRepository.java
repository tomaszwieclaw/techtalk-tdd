package com.teaminternational.tddgym.domain.port;

import com.teaminternational.tddgym.domain.model.Member;

public interface MemberRepository {
    void save(Member member);
}
