package com.teaminternational.tddgym.application.ui;

import com.teaminternational.tddgym.application.model.CreateNewMemberSubscriptionRequest;
import com.teaminternational.tddgym.application.model.CreateNewMemberSubscriptionResponse;
import com.teaminternational.tddgym.application.service.MemberSubscriptionApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberSubscriptionController {
    private final MemberSubscriptionApiService apiService;

    @PostMapping
    public ResponseEntity<CreateNewMemberSubscriptionResponse> create(
            @RequestBody CreateNewMemberSubscriptionRequest request
    ) {
        return apiService.create(request);
    }
}
