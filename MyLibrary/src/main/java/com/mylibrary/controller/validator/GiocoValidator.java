package com.mylibrary.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mylibrary.model.Gioco;
import com.mylibrary.service.GiocoService;

@Component
public class GiocoValidator implements Validator {
	
	@Autowired
	private GiocoService giocoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Gioco.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Gioco gioco = (Gioco)target;
		
		if(this.giocoService.alreadyExists(gioco)) {
			errors.reject("gioco.duplicato");
		}
	}

}
