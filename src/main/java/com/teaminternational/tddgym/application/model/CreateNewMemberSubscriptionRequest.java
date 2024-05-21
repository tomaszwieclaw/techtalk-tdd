package com.teaminternational.tddgym.application.model;

import com.teaminternational.tddgym.domain.model.BodyType;
import com.teaminternational.tddgym.domain.model.Sex;
import com.teaminternational.tddgym.domain.model.TrainingGoal;

import java.time.LocalDate;
import java.util.Set;

public record CreateNewMemberSubscriptionRequest(
        String firstName,
        String lastName,
        LocalDate dob,
        Sex sex,
        BodyType bodyType,
        Set<TrainingGoal> trainingGoals
) {
}
