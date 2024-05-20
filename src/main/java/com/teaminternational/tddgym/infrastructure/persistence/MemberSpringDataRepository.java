package com.teaminternational.tddgym.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberSpringDataRepository extends JpaRepository<MemberJpaEntity, UUID> {
}
