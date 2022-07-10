package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import com.mylibrary.model.Credentials;
import com.mylibrary.model.User;
import com.mylibrary.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mylibrary.model.Commento;
import com.mylibrary.service.CommentoService;

@Controller
public class CommentoController {
	
	@Autowired
	private CommentoService commentoService;
	@Autowired
	private CredentialsService credentialsService;
	
	@PostMapping("/commento")
	public String addCommento(@Valid @ModelAttribute Commento commento, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.commentoService.save(commento);
			model.addAttribute("commento", commento);
			return "commento.html";
		}
		
		return "commentoForm.html";
	}
	
	@GetMapping("/commenti")
	public String getAllCommenti(Model model) {
		List<Commento> commenti = this.commentoService.findAllCommenti();
		model.addAttribute("commenti", commenti);
		return "commenti.html";
	}
	
	@GetMapping("/commentoForm/{id}")
	public String getNewCommento(Model model, @PathVariable("id") Long id) {

		Credentials credentials =(Credentials) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		String userName = this.credentialsService.getCredentials(SecurityContextHolder.getContext().getAuthentication().getName()).getUser().getNome();
		model.addAttribute("id", id);
		model.addAttribute("commento", new Commento());
		model.addAttribute("username", userName);
		return "commentoForm.html";
	}
	
	@GetMapping("/commento/{id}")
	public String getCommento(@PathVariable("id") Long id, Model model) {
		Commento commento = this.commentoService.findCommentoById(id);
		model.addAttribute("commento", commento);
		return "commento.html";
	}
	
}
