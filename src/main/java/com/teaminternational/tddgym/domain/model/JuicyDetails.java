package com.teaminternational.tddgym.domain.model;

import java.util.Set;

public record JuicyDetails(
        BodyType bodyType,
        Set<TrainingGoal> trainingGoals
) {
}
