package com.nahowo.rushTicket.dto.validator;

import com.nahowo.rushTicket.dto.annotation.ValidEventCreate;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.DateSeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.EventTimeAndPrice;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventCreateValidator implements
    ConstraintValidator<ValidEventCreate, EventCreateRequest> {

    @Override
    public void initialize(ValidEventCreate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventCreateRequest value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (!checkBookingStartIsBeforeEndTime(value)) {
            buildConstraintViolationMessage("예매 시작 일시는 종료 일시보다 이전이어야 합니다. ", context);
            return false;
        }
        if (!checkOverlappingEvents(value)) {
            buildConstraintViolationMessage("모든 공연 시간은 겹칠 수 없습니다. ", context);
            return false;
        }
        if (!checkBookingEndTimeIsBeforeFirstEventTime(value)) {
            buildConstraintViolationMessage("예매 종료 일시는 첫 공연 일시보다 이전이어야 합니다. ", context);
            return false;
        }
        return true;
    }

    private void buildConstraintViolationMessage(String message,
        ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation();
    }

    private boolean checkBookingStartIsBeforeEndTime(EventCreateRequest value) {
        return value.bookingStartTime().isBefore(value.bookingEndTime());
    }

    private boolean checkOverlappingEvents(EventCreateRequest value) {
        List<EventTimeAndPrice> eventTimeAndPrices = value.eventTimeAndPrices();
        List<DateSeatGroupPrice> allEventTimes = eventTimeAndPrices.stream()
            .flatMap(event -> event.dateSeatGroupPrices().stream())
            .collect(Collectors.toList());
        allEventTimes.sort(Comparator.comparing(DateSeatGroupPrice::eventStartTime));
        for (int i = 0; i < allEventTimes.size() - 1; i++) {
            LocalDateTime currentEndTime = allEventTimes.get(i).eventEndTime();
            LocalDateTime nextStartTime = allEventTimes.get(i + 1).eventStartTime();
            if (currentEndTime.isAfter(nextStartTime) || currentEndTime.isEqual(nextStartTime)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBookingEndTimeIsBeforeFirstEventTime(EventCreateRequest value) {
        LocalDateTime bookingEndTime = value.bookingEndTime();
        List<EventTimeAndPrice> eventTimeAndPrices = value.eventTimeAndPrices();
        Optional<LocalDateTime> firstEventStartTime = eventTimeAndPrices.stream()
            .flatMap(event -> event.dateSeatGroupPrices().stream())
            .map(DateSeatGroupPrice::eventStartTime)
            .min(LocalDateTime::compareTo);
        if (!bookingEndTime.isBefore(firstEventStartTime.get())) {
            return false;
        }
        return true;
    }
}