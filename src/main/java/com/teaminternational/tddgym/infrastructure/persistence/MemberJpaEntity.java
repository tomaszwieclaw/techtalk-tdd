package com.teaminternational.tddgym.infrastructure.persistence;

import com.teaminternational.tddgym.domain.model.BodyType;
import com.teaminternational.tddgym.domain.model.Sex;
import com.teaminternational.tddgym.domain.model.TrainingGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@Entity
public class MemberJpaEntity {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @ElementCollection
    private Set<TrainingGoal> trainingGoals;

    private OffsetDateTime createdAt;

    private OffsetDateTime lastUpdatedAt;
}
