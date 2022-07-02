package com.mylibrary.controller;

import com.mylibrary.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@Autowired
	private FilmService filmService;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("films", filmService.findAllFilms());

		return "index";
	}

}
