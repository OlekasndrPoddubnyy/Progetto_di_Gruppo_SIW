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

import com.mylibrary.model.Commento;
import com.mylibrary.service.CommentoService;

@Controller
public class CommentoController {
	
	@Autowired
	private CommentoService commentoService;
	
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
	
	@GetMapping("/commentoForm")
	public String getCommento(Model model) {
		model.addAttribute("commento", new Commento());
		return "commentoForm.html";
	}
	
	@GetMapping("/commento/{id}")
	public String getCommento(@PathVariable("id") Long id, Model model) {
		Commento commento = this.commentoService.findCommentoById(id);
		model.addAttribute("commento", commento);
		return "commento.html";
	}
	
}
