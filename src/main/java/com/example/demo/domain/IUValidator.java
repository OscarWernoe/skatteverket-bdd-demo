package com.example.demo.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class IUValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return IU.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"inkomsttagare", "inkomsttagare.error");
    }
}
