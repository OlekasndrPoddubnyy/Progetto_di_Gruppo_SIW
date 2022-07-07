package com.mylibrary.controller.validator;

import com.mylibrary.model.SerieTv;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SerieTvValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SerieTv.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
