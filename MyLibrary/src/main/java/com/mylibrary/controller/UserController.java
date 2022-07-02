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
		model.addAttribute("serieTvPreferiti", user.getSerieTvPreferite());
		
		return "user/preferiti.html";
	}
	
	@PostMapping("/user/giocoPreferito/{giocoId}/{userId}")
	public String addGiocoToPreferiti(@PathVariable("giocoId") Long giocoId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.addGiocoToPreferiti(user, this.giocoService.findGiocoById(giocoId));
		
		return "index.html";
	}
	
	@PostMapping("/user/filmPreferito/{filmId}/{userId}")
	public String addFilmToPreferiti(@PathVariable("filmId") Long filmId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);
		
		this.userService.addFilmToPreferiti(user, this.filmService.findFilmById(filmId));
		
		return "index.html";
	}
	
	@PostMapping("/user/serieTvPreferita/{serieTvId}/{userId}")
	public String addSerieTvToPreferiti(@PathVariable("serieTvId") Long serieTvId, @PathVariable("userId") Long userId, Model model) {
		User user = this.userService.getUser(userId);

		this.userService.addSerieTvToPreferiti(user, this.serieTvService.findById(serieTvId));
		
		return "index.html";
	}
	
}
