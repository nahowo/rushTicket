package com.nahowo.rushTicket.dto.annotation;

import com.nahowo.rushTicket.dto.validator.EventTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventTimeValidator.class)
public @interface ValidEventTime {
    String message() default "공연 날짜와 공연 일정 일시는 일치해야 합니다. ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
