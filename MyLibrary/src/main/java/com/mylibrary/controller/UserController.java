package com.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mylibrary.model.Credentials;
import com.mylibrary.model.User;
import com.mylibrary.service.CredentialsService;
import com.mylibrary.service.FilmService;
import com.mylibrary.service.GiocoService;
import com.mylibrary.service.LibroService;
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
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	private User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			Credentials credentials = this.credentialsService.getCredentials(currentUserName);
			user = credentials.getUser();
		}
		
		return user;
	}
	
	@GetMapping("/user/preferiti")
	public String getPreferiti(Model model) {
		User user = this.getCurrentUser();
		
		model.addAttribute("filmPreferiti", user.getFilmPreferiti());
		model.addAttribute("giochiPreferiti", user.getGiochiPreferiti());
		model.addAttribute("serieTvPreferite", user.getSerieTvPreferite());
		model.addAttribute("libriPreferiti", user.getLibriPreferiti());
		
		return "/home";
	}
	
	/* AGGIUNTA PREFERITI */
	
	@GetMapping("/user/addGiocoPreferito/{giocoId}")
	public String addGiocoToPreferiti(@PathVariable("giocoId") Long giocoId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.addGiocoToPreferiti(user, this.giocoService.findGiocoById(giocoId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/addFilmPreferito/{filmId}")
	public String addFilmToPreferiti(@PathVariable("filmId") Long filmId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.addFilmToPreferiti(user, this.filmService.findFilmById(filmId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/addSerieTvPreferita/{serieTvId}")
	public String addSerieTvToPreferiti(@PathVariable("serieTvId") Long serieTvId, Model model) {
		User user = this.getCurrentUser();

		this.userService.addSerieTvToPreferiti(user, this.serieTvService.findById(serieTvId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/addLibroPreferito/{libroId}")
	public String addibroToPreferiti(@PathVariable("libroId") Long libroId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.addLibroToPreferiti(user, this.libroService.findById(libroId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	/* CANCELLAZIONE PREFERITI */
	
	@GetMapping("/user/deleteGiocoPreferito/{giocoId}")
	public String deleteGiocoFromPreferiti(@PathVariable("giocoId") Long giocoId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.deleteGiocoFromPreferiti(user, this.giocoService.findGiocoById(giocoId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/deleteFilmPreferito/{filmId}")
	public String deleteFilmFromPreferiti(@PathVariable("filmId") Long filmId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.deleteFilmFromPreferiti(user, this.filmService.findFilmById(filmId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/deleteSerieTvPreferita/{serieTvId}")
	public String deleteSerieTvFromPreferiti(@PathVariable("serieTvId") Long serieTvId, Model model) {
		User user = this.getCurrentUser();

		this.userService.deleteSerieTvFromPreferiti(user, this.serieTvService.findById(serieTvId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	@GetMapping("/user/deleteLibroPreferito/{libroId}")
	public String deleteLibroFromPreferiti(@PathVariable("libroId") Long libroId, Model model) {
		User user = this.getCurrentUser();
		
		this.userService.deleteLibroFromPreferiti(user, this.libroService.findById(libroId));
		model.addAttribute("user", user);
		
		return "/home";
	}
	
}
