package com.mylibrary.controller.validator;

import com.mylibrary.model.Episodio;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EpisodioValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Episodio.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
