package com.teaminternational.tddgym.domain.usecase

import com.teaminternational.tddgym.domain.model.*
import com.teaminternational.tddgym.infrastructure.persistence.MemberJpaEntity
import com.teaminternational.tddgym.infrastructure.persistence.MemberSpringDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

@ContextConfiguration
@SpringBootTest
class SubscribeNewMemberUseCaseTest extends Specification {
    @Autowired
    MemberSpringDataRepository memberSpringDataRepository
    @Autowired
    SubscribeNewMemberUseCase sut

    def """
        GIVEN new member details are provided
        WHEN SubscribeNewMemberUseCase executed
        THEN new member details are returned
        AND new member details are saved in the repository
        """() {
        given:
        def command = new SubscribeNewMemberCommand(
                "Tomasz",
                "Wieclaw",
                LocalDate.of(1989, Month.FEBRUARY, 26),
                Sex.MAN,
                Optional.of(
                        new JuicyDetails(
                                BodyType.ENHANCED,
                                Set.of(TrainingGoal.MUSCLE_MASS, TrainingGoal.STRENGTH)
                        )
                )
        )
        and:
        memberSpringDataRepository.findAll().isEmpty()

        when:
        MemberDTO result = sut.execute(command)

        then:
        result != null
        result.id() != null
        result.createdAt() != null
        result.lastUpdatedAt() != null
        result.lastUpdatedAt() == result.createdAt()
        result.firstName() == "Tomasz"
        result.lastName() == "Wieclaw"
        result.dob() == LocalDate.of(1989, Month.FEBRUARY, 26)
        def juicyDetails = result.juicyDetails().get()
        juicyDetails.bodyType() == BodyType.ENHANCED
        juicyDetails.trainingGoals().size() == 2
        juicyDetails.trainingGoals().contains(TrainingGoal.MUSCLE_MASS)
        juicyDetails.trainingGoals().contains(TrainingGoal.STRENGTH)

        and:
        memberSpringDataRepository.findAll().size() == 1
        def saved = memberSpringDataRepository.findAll().get(0)
        saved.id == result.id()
        saved.firstName == result.firstName()
        saved.createdAt == result.createdAt()
        saved.lastUpdatedAt == result.lastUpdatedAt()
        saved.firstName == result.firstName()
        saved.lastName == result.lastName()
        saved.dob == result.dob()
        saved.bodyType == result.juicyDetails().get().bodyType()
        saved.trainingGoals == result.juicyDetails().get().trainingGoals()
    }
}
