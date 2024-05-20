package com.teaminternational.tddgym.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public record Member(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dob,
        Sex sex,
        Optional<JuicyDetails> juicyDetails,
        OffsetDateTime createdAt,
        OffsetDateTime lastUpdatedAt
) {

    public MemberDTO toDTO() {
        return new MemberDTO(
                id(),
                firstName(),
                lastName(),
                dob(),
                sex(),
                juicyDetails(),
                createdAt(),
                lastUpdatedAt()
        );
    }
}
