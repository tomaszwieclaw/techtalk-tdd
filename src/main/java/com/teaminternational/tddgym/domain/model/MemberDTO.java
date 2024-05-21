package com.teaminternational.tddgym.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record MemberDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dob,
        Sex sex,
        JuicyDetails juicyDetails,
        OffsetDateTime createdAt,
        OffsetDateTime lastUpdatedAt
) {
}
