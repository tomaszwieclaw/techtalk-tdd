package com.teaminternational.tddgym.domain.model;

import java.time.LocalDate;

public record SubscribeNewMemberCommand(
        String firstName,
        String lastName,
        LocalDate dob,
        Sex sex
) {
}
