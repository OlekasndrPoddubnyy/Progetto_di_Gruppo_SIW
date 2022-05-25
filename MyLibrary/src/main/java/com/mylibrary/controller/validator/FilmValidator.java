package com.mylibrary.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mylibrary.model.Film;
import com.mylibrary.service.FilmService;

@Component
public class FilmValidator implements Validator {
	
	@Autowired
	private FilmService filmService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Film.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.filmService.alreadyExists((Film)target)) {
			errors.reject("film.duplicato");
		}
	}
	
}
