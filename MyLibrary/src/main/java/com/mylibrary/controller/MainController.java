package com.mylibrary.controller;

import com.mylibrary.service.FilmService;
import com.mylibrary.service.GiocoService;
import com.mylibrary.service.LibroService;
import com.mylibrary.service.SerieTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@Autowired
	private FilmService filmService;

	@Autowired
	private SerieTvService serieTvService;

	@Autowired
	private GiocoService giocoService;

	@Autowired
	private LibroService libroService;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("films", filmService.findAllFilms());
		model.addAttribute("series", serieTvService.serieTvs());
		model.addAttribute("giochi", giocoService.findAllGiochi());
		model.addAttribute("libri", libroService.libri());
		return "index";
	}

}
