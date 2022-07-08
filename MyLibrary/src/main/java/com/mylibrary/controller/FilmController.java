package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mylibrary.controller.validator.FilmValidator;
import com.mylibrary.model.Film;
import com.mylibrary.service.FilmService;

@Controller
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private FilmValidator filmValidator;

	@RequestMapping(value="/admin/film", method = RequestMethod.GET)
	public String addFilm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm";
	}
	
	@RequestMapping(value = "/admin/film", method = RequestMethod.POST)
	public String addFilm(@Valid @ModelAttribute("film") Film film,
							  Model model, BindingResult bindingResult) {
		this.filmValidator.validate(film, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.filmService.save(film);
			model.addAttribute("films", this.filmService.findAllFilms());
			return "admin/home.html";
		}
		return "filmForm";
	}
	
	@PostMapping("/deleteFilm/{id}")
	public String deleteFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		this.filmService.deleteFilm(film);
		return "films";
	}
	
	@GetMapping("/film/{id}")
	public String getFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		model.addAttribute("film", film);
		return "film";
	}
	
	@GetMapping("film")
	public String getAllFilms(Model model) {
		List<Film> films = this.filmService.findAllFilms();
		model.addAttribute("films", films);
		return "index";
	}
	
	@GetMapping("/admin/updateFilmForm/{id}")
	public String modifyFilmData(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		model.addAttribute("film", film);
		return "filmFormUpdate.html";
	}
	
	@PostMapping("/admin/updateFilm")
	public String modifyFilmData(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.filmService.save(film);
			model.addAttribute("films", this.filmService.findAllFilms());
			return "admin/home.html";
		}
		
		return "filmFormUpdate.html";
	}
	
}
