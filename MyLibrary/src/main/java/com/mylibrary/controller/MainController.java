package com.mylibrary.controller;

import com.mylibrary.model.Credentials;
import com.mylibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private UserService userService;

	@Autowired
	private CredentialsService credentialsService;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			Credentials credentials = this.credentialsService.getCredentials(currentUserName);
			model.addAttribute("user", this.userService.getUser(credentials.getUser().getId()));
		}
		model.addAttribute("films", filmService.findAllFilms());
		model.addAttribute("series", serieTvService.serieTvs());
		model.addAttribute("giochi", giocoService.findAllGiochi());
		model.addAttribute("libri", libroService.libri());
		return "index";
	}

}
