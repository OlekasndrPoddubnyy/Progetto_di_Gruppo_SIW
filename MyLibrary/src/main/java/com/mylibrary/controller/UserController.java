package com.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mylibrary.model.User;
import com.mylibrary.service.FilmService;
import com.mylibrary.service.GiocoService;
import com.mylibrary.service.SerieTvService;
import com.mylibrary.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private GiocoService giocoService;
	
	@Autowired
	private SerieTvService serieTvService;
	
	@GetMapping("/user/preferiti/{userId}")
	public String getPreferiti(@PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		model.addAttribute("filmPreferiti", user.getFilmPreferiti());
		model.addAttribute("giochiPreferiti", user.getGiochiPreferiti());
		model.addAttribute("serieTvPreferite", user.getSerieTvPreferite());
		
		return "user/preferiti.html";
	}
	
	/* AGGIUNTA PREFERITI */
	
	@PostMapping("/user/addGiocoPreferito/{giocoId}/{userId}")
	public String addGiocoToPreferiti(@PathVariable("giocoId") Long giocoId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.addGiocoToPreferiti(user, this.giocoService.findGiocoById(giocoId));
		
		return "user/preferiti.html";
	}
	
	@PostMapping("/user/addFilmPreferito/{filmId}/{userId}")
	public String addFilmToPreferiti(@PathVariable("filmId") Long filmId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.addFilmToPreferiti(user, this.filmService.findFilmById(filmId));
		
		return "user/preferiti.html";
	}
	
	@PostMapping("/user/addSerieTvPreferita/{serieTvId}/{userId}")
	public String addSerieTvToPreferiti(@PathVariable("serieTvId") Long serieTvId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);

		this.userService.addSerieTvToPreferiti(user, this.serieTvService.findById(serieTvId));
		
		return "user/preferiti.html";
	}
	
	/* CANCELLAZIONE PREFERITI */
	
	@PostMapping("/user/deleteGiocoPreferito/{giocoId}/{userId}")
	public String deleteGiocoFromPreferiti(@PathVariable("giocoId") Long giocoId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.deleteGiocoFromPreferiti(user, this.giocoService.findGiocoById(giocoId));
		
		return "user/preferiti.html";
	}
	
	@PostMapping("/user/deleteFilmPreferito/{filmId}/{userId}")
	public String deleteFilmFromPreferiti(@PathVariable("filmId") Long filmId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.deleteFilmFromPreferiti(user, this.filmService.findFilmById(filmId));
		
		return "user/preferiti.html";
	}
	
	@PostMapping("/user/deleteSerieTvPreferita/{serieTvId}/{userId}")
	public String deleteSerieTvFromPreferiti(@PathVariable("serieTvId") Long serieTvId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);

		this.userService.deleteSerieTvFromPreferiti(user, this.serieTvService.findById(serieTvId));
		
		return "user/preferiti.html";
	}
	
}
