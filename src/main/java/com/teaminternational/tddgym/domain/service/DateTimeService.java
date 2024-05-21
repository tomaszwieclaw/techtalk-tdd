package com.teaminternational.tddgym.domain.service;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class DateTimeService {

    public OffsetDateTime currentOffsetDateTime() {
        return OffsetDateTime.now();
    }
}
