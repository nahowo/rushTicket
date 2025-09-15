package com.nahowo.rushTicket.dto.validator;

import com.nahowo.rushTicket.dto.annotation.ValidBookingTime;
import com.nahowo.rushTicket.dto.annotation.ValidEventTime;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.DateSeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.EventTimeAndPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.List;

public class EventTimeValidator implements
    ConstraintValidator<ValidEventTime, EventTimeAndPrice> {

    @Override
    public void initialize(ValidEventTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventTimeAndPrice value, ConstraintValidatorContext context) {
        LocalDate localDate = value.eventDate();
        List<DateSeatGroupPrice> dateSeatGroupPrices = value.dateSeatGroupPrices();
        for (DateSeatGroupPrice dateSeatGroupPrice : dateSeatGroupPrices) {
            LocalDate eventDate = dateSeatGroupPrice.eventStartTime().toLocalDate();
            if (!localDate.isEqual(eventDate)) {
                return false;
            }
        }
        return true;
    }
}
