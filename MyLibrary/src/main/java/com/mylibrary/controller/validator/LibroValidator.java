package com.mylibrary.controller.validator;

import com.mylibrary.model.Libro;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class LibroValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Libro.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
