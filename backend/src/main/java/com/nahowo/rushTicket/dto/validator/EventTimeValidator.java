package com.nahowo.rushTicket.dto.validator;

import com.nahowo.rushTicket.dto.annotation.ValidEventTime;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.DateSeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.EventTimeAndPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventTimeValidator implements
    ConstraintValidator<ValidEventTime, EventTimeAndPrice> {

    @Override
    public void initialize(ValidEventTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventTimeAndPrice value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (!checkEventDateMatchesEventStartTimes(value)) {
            buildConstraintViolationMessage("공연 날짜와 공연 일정 일시는 일치해야 합니다. ", context);
            return false;
        }
        if (!checkEventStartTimeIsBeforeEndTime(value)) {
            buildConstraintViolationMessage("공연 시작 시간은 공연 종료 시간보다 이전이어야 합니다. ", context);
            return false;
        }
        return true;
    }

    private void buildConstraintViolationMessage(String message,
        ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation();
    }

    private boolean checkEventDateMatchesEventStartTimes(EventTimeAndPrice value) {
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

    private boolean checkEventStartTimeIsBeforeEndTime(EventTimeAndPrice value) {
        List<DateSeatGroupPrice> dateSeatGroupPrices = value.dateSeatGroupPrices();
        for (DateSeatGroupPrice dateSeatGroupPrice : dateSeatGroupPrices) {
            LocalDateTime eventStartTime = dateSeatGroupPrice.eventStartTime();
            LocalDateTime eventEndTime = dateSeatGroupPrice.eventEndTime();
            if (!eventEndTime.isAfter(eventStartTime)) {
                return false;
            }
        }
        return true;
    }
}
