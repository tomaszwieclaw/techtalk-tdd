package com.teaminternational.tddgym.infrastructure.persistence;

import com.teaminternational.tddgym.domain.model.JuicyDetails;
import com.teaminternational.tddgym.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

    public MemberJpaEntity toDatabaseEntity(final Member domainEntity) {
        return new MemberJpaEntity(
                domainEntity.id(),
                domainEntity.firstName(),
                domainEntity.lastName(),
                domainEntity.dob(),
                domainEntity.sex(),
                domainEntity.juicyDetails().bodyType(),
                domainEntity.juicyDetails().trainingGoals(),
                domainEntity.createdAt(),
                domainEntity.lastUpdatedAt()
        );
    }

    public Member toDomainEntity(final MemberJpaEntity databaseEntity) {
        return new Member(
                databaseEntity.getId(),
                databaseEntity.getFirstName(),
                databaseEntity.getLastName(),
                databaseEntity.getDob(),
                databaseEntity.getSex(),
                new JuicyDetails(
                        databaseEntity.getBodyType(),
                        databaseEntity.getTrainingGoals()
                ),
                databaseEntity.getCreatedAt(),
                databaseEntity.getLastUpdatedAt()
        );
    }
}
