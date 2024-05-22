package com.teaminternational.tddgym.domain.usecase

import com.teaminternational.tddgym.domain.model.*
import com.teaminternational.tddgym.domain.port.MemberRepository
import com.teaminternational.tddgym.domain.service.DateTimeService
import com.teaminternational.tddgym.infrastructure.event.EventQueue
import com.teaminternational.tddgym.infrastructure.persistence.MemberSpringDataRepository
import org.spockframework.spring.SpringBean
import org.spockframework.spring.SpringSpy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@ContextConfiguration
@SpringBootTest
class SubscribeNewMemberUseCaseTest extends Specification {
    static final DATE_TIME_TEST_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSZ")

    @SpringSpy
    MemberRepository memberRepository
    @SpringBean
    DateTimeService dateTimeService = Mock()
    @Autowired
    MemberSpringDataRepository memberSpringDataRepository
    @Autowired
    EventQueue eventQueue;
    @Autowired
    SubscribeNewMemberUseCase sut

    def """
        GIVEN a valid SubscribeNewMemberCommand is provided
        WHEN SubscribeNewMemberUseCase executed
        THEN newly created MemberDTO is returned
        AND Member is saved in the repository
        AND NEW_MEMBER_SUBSCRIBED event was sent
        """() {
        given:
        def memberId = UUID.fromString('380a92f8-75f4-48d3-8b6f-e53cfb3490d8')
        def now = OffsetDateTime.of(
                2024, 5, 22, 15, 0, 30, 555, ZoneOffset.UTC
        )
        memberRepository.nextId() >> memberId
        dateTimeService.currentOffsetDateTime() >> now
        and:
        def command = new SubscribeNewMemberCommand(
                "Tomasz",
                "Wieclaw",
                LocalDate.of(1989, Month.FEBRUARY, 26),
                Sex.MAN,
                new JuicyDetails(
                        BodyType.ENHANCED,
                        Set.of(TrainingGoal.MUSCLE_MASS, TrainingGoal.STRENGTH)
                )
        )
        and:
        memberSpringDataRepository.findAll().isEmpty()
        eventQueue.getAll().isEmpty()

        when:
        MemberDTO result = sut.execute(command)

        then:
        result != null
        result.id() == memberId
        result.createdAt() == now
        result.lastUpdatedAt() == now
        result.lastUpdatedAt() == result.createdAt()
        result.firstName() == "Tomasz"
        result.lastName() == "Wieclaw"
        result.dob() == LocalDate.of(1989, Month.FEBRUARY, 26)
        def juicyDetails = result.juicyDetails()
        juicyDetails.bodyType() == BodyType.ENHANCED
        juicyDetails.trainingGoals().size() == 2
        juicyDetails.trainingGoals().contains(TrainingGoal.MUSCLE_MASS)
        juicyDetails.trainingGoals().contains(TrainingGoal.STRENGTH)

        and:
        memberSpringDataRepository.findAll().size() == 1
        def saved = memberSpringDataRepository.findAll().get(0)
        saved.id == memberId
        saved.firstName == result.firstName()
        saved.createdAt.format(DATE_TIME_TEST_FORMATTER) == now.format(DATE_TIME_TEST_FORMATTER)
        saved.lastUpdatedAt.format(DATE_TIME_TEST_FORMATTER) == now.format(DATE_TIME_TEST_FORMATTER)
        saved.firstName == result.firstName()
        saved.lastName == result.lastName()
        saved.dob == result.dob()
        saved.bodyType == result.juicyDetails().bodyType()
        saved.trainingGoals == result.juicyDetails().trainingGoals()

        and:
        eventQueue.getAll().size() == 1
        def sentEvent = eventQueue.getAll().get(0)
        sentEvent.type == DomainEvent.Type.NEW_MEMBER_SUBSCRIBED
        sentEvent.content == memberId.toString()
        sentEvent.createdAt.format(DATE_TIME_TEST_FORMATTER) == now.format(DATE_TIME_TEST_FORMATTER)
    }
}
