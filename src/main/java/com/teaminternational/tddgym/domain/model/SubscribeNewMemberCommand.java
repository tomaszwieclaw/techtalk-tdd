package com.teaminternational.tddgym.domain.model;

import java.time.LocalDate;
import java.util.Optional;

public record SubscribeNewMemberCommand(
        String firstName,
        String lastName,
        LocalDate dob,
        Sex sex,
        Optional<JuicyDetails> juicyDetails
) {
}
