package com.capgemini.entities.validations.dates;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDates, LocalDateTime>{

    @Override
    public void initialize(ValidDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext context) {
       if(date.getDayOfYear() <= LocalDateTime.now().getDayOfYear() && date.getYear() <= LocalDateTime.now().getYear()){
           return true;
       } else return false;
    }
    
}
