package com.capgemini.entities.validations.dates;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=DateValidator.class) 
public @interface ValidDates {
    String message() default "{message.id}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}