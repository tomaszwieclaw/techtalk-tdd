package com.teaminternational.tddgym.application.service;

import com.teaminternational.tddgym.application.model.CreateNewMemberSubscriptionRequest;
import com.teaminternational.tddgym.application.model.CreateNewMemberSubscriptionResponse;
import com.teaminternational.tddgym.domain.model.JuicyDetails;
import com.teaminternational.tddgym.domain.model.MemberDTO;
import com.teaminternational.tddgym.domain.model.SubscribeNewMemberCommand;
import com.teaminternational.tddgym.domain.usecase.SubscribeNewMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberSubscriptionApiService {
    private final SubscribeNewMemberUseCase subscribeNewMemberUseCase;

    public ResponseEntity<CreateNewMemberSubscriptionResponse> create(
            final CreateNewMemberSubscriptionRequest request
    ) {
        final MemberDTO memberDTO = subscribeNewMemberUseCase.execute(
                new SubscribeNewMemberCommand(
                        request.firstName(),
                        request.lastName(),
                        request.dob(),
                        request.sex(),
                        new JuicyDetails(
                                request.bodyType(),
                                request.trainingGoals()
                        )
                )
        );
        var response = new CreateNewMemberSubscriptionResponse(memberDTO.id().toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
