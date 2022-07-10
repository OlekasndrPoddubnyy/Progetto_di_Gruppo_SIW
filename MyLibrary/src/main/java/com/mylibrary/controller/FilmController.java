package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import com.mylibrary.model.Commento;
import com.mylibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.mylibrary.controller.validator.FilmValidator;
import com.mylibrary.model.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private SerieTvService serieTvService;

	@Autowired
	private LibroService libroService;

	@Autowired
	private GiocoService giocoService;
	
	@Autowired
	private FilmValidator filmValidator;

	@Autowired
	private CommentoService commentoService;


	@RequestMapping(value="/admin/film", method = RequestMethod.GET)
	public String addFilm(Model model) {
		model.addAttribute("film", new Film());
		return "forms/filmForm";
	}
	
	@RequestMapping(value = "/admin/film", method = RequestMethod.POST)
	public String addFilm(@Valid @ModelAttribute("film") Film film,
							  Model model, BindingResult bindingResult) {
		this.filmValidator.validate(film, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.filmService.save(film);
			model.addAttribute("films", filmService.findAllFilms());
			model.addAttribute("series", serieTvService.serieTvs());
			model.addAttribute("giochi", giocoService.findAllGiochi());
			model.addAttribute("libri", libroService.libri());
			return "admin/home";
		}
		return "forms/filmForm";
	}
	
	@GetMapping("/deleteFilm/{id}")
	public String deleteFilm(@PathVariable("id") Long id, Model model) {
		Film film = this.filmService.findFilmById(id);
		this.filmService.deleteFilm(film);
		model.addAttribute("films", filmService.findAllFilms());
		model.addAttribute("series", serieTvService.serieTvs());
		model.addAttribute("giochi", giocoService.findAllGiochi());
		model.addAttribute("libri", libroService.libri());
		return "admin/home";
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
		return "forms/filmFormUpdate";
	}
	
	@PostMapping("/admin/filmUpdate")
	public String modifyFilmData(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.filmService.updateFilm(film);
			model.addAttribute("films", filmService.findAllFilms());
			model.addAttribute("series", serieTvService.serieTvs());
			model.addAttribute("giochi", giocoService.findAllGiochi());
			model.addAttribute("libri", libroService.libri());
			return "admin/home";
		}
		model.addAttribute("film",film);
		return "forms/filmFormUpdate";
	}

	@PostMapping("/filmCommento/{idF}/{username}")
	public String inserisciCommento(Model model, @PathVariable("username") String username, @PathVariable("idF") Long idF, @ModelAttribute("commento")Commento commento) {


		commento.setUsername(username);
		this.commentoService.save(commento);
		this.filmService.aggiungiCommentoAFilm(idF, commento.getId());
		model.addAttribute("film", this.filmService.findFilmById(idF));
		return "film";
	}
	
}
