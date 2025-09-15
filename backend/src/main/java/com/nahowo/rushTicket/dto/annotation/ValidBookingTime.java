package com.nahowo.rushTicket.dto.annotation;

import com.nahowo.rushTicket.dto.validator.BookingTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookingTimeValidator.class)
public @interface ValidBookingTime {
    String message() default "예매 시작 일시는 종료 일시보다 이전이어야 합니다. ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
