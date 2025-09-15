package com.nahowo.rushTicket.dto.validator;

import com.nahowo.rushTicket.dto.annotation.ValidBookingTime;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookingTimeValidator implements
    ConstraintValidator<ValidBookingTime, EventCreateRequest> {

    @Override
    public void initialize(ValidBookingTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventCreateRequest request, ConstraintValidatorContext context) {
        return request.bookingStartTime().isBefore(request.bookingEndTime());
    }
}
