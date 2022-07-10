package com.mylibrary.controller;

import com.mylibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mylibrary.controller.validator.CredentialsValidator;
import com.mylibrary.controller.validator.UserValidator;
import com.mylibrary.model.Credentials;
import com.mylibrary.model.User;

@Controller
public class AuthenticationController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CredentialsValidator credentialsValidator;

	@Autowired
	private FilmService filmService;

	@Autowired
	private SerieTvService serieTvService;

	@Autowired
	private LibroService libroService;

	@Autowired
	private  UserService userService;

	@Autowired
	private GiocoService giocoService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm (Model model) {
		return "loginForm";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "index";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("films", filmService.findAllFilms());
			model.addAttribute("series", serieTvService.serieTvs());
			model.addAttribute("giochi", giocoService.findAllGiochi());
			model.addAttribute("libri", libroService.libri());

			return "admin/home";
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			Credentials credentials1 = this.credentialsService.getCredentials(currentUserName);
			model.addAttribute("user", this.userService.getUser(credentials1.getUser().getId()));
		}
		return "/home";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
							   BindingResult userBindingResult,
							   @ModelAttribute("credentials") Credentials credentials,
							   BindingResult credentialsBindingResult,
							   Model model) {

		// validate user and credentials fields
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		// if neither of them had invalid contents, store the User and the Credentials into the DB
		if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
			// set the user and store the credentials;
			// this also stores the User, thanks to Cascade.ALL policy
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessfull";
		}
		return "registerUser";
	}


}
