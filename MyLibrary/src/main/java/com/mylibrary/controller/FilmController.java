package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mylibrary.controller.validator.FilmValidator;
import com.mylibrary.model.Film;
import com.mylibrary.service.FilmService;

@Controller
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private FilmValidator filmValidator;
	
	@PostMapping("/film")
	public String addFilm(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model) {
		this.filmValidator.validate(film, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.filmService.save(film);
			model.addAttribute("film", film);
			return "film.html";
		}
		
		return "filmForm.html";
	}
	
	@GetMapping("/deleteFilm/{id}")
	public String toDeleteFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		model.addAttribute("film", film);
		return "toDeleteFilm.html";
	}
	
	@PostMapping("/deleteFilm")
	public String deleteFilm(Model model) {
		Film film = (Film)model.getAttribute("film");
		this.filmService.deleteFilm(film);
		return "films.html";
	}
	
	@GetMapping("/filmForm")
	public String getFilm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm.html";
	}
	
	@GetMapping("/film/{id}")
	public String getFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		model.addAttribute("film", film);
		return "film.html";
	}
	
	@GetMapping("/films")
	public String getAllFilms(Model model) {
		List<Film> films = this.filmService.findAllFilms();
		model.addAttribute("films", films);
		return "films.html";
	}
	
}
