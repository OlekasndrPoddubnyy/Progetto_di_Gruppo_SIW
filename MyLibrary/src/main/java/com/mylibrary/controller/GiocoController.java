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
import com.mylibrary.model.Gioco;
import com.mylibrary.service.FilmService;
import com.mylibrary.service.GiocoService;

@Controller
public class GiocoController {
	
	@Autowired
	private GiocoService giocoService;
	
	@PostMapping("/gioco")
	public String addFilm(@Valid @ModelAttribute("gioco") Gioco gioco, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.giocoService.save(gioco);
			model.addAttribute("gioco", gioco);
			return "gioco.html";
		}
		
		return "giocoForm.html";
	}
	
	@GetMapping("/deleteGioco/{id}")
	public String toDeleteGioco(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		model.addAttribute("gioco", gioco);
		return "toDeleteGioco.html";
	}
	
	@PostMapping("/deleteGioco")
	public String deleteGioco(Model model) {
		Gioco gioco = (Gioco)model.getAttribute("gioco");
		this.giocoService.deleteGioco(gioco);
		return "giochi.html";
	}
	
	@GetMapping("/giocoForm")
	public String getGioco(Model model) {
		model.addAttribute("gioco", new Gioco());
		return "giocoForm.html";
	}
	
	@GetMapping("/gioco/{id}")
	public String getFilm(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		model.addAttribute("gioco", gioco);
		return "gioco.html";
	}
	
	@GetMapping("/giochi")
	public String getAllFilms(Model model) {
		List<Gioco> giochi = this.giocoService.findAllGiochi();
		model.addAttribute("giochi", giochi);
		return "giochi.html";
	}
	
}
