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

import com.mylibrary.controller.validator.GiocoValidator;
import com.mylibrary.model.Gioco;
import com.mylibrary.service.GiocoService;

@Controller
public class GiocoController {
	
	@Autowired
	private GiocoService giocoService;
	
	@Autowired
	private GiocoValidator giocoValidator;
	
	@PostMapping("/gioco")
	public String addFilm(@Valid @ModelAttribute("gioco") Gioco gioco, BindingResult bindingResult, Model model) {
		this.giocoValidator.validate(gioco, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.giocoService.save(gioco);
			model.addAttribute("gioco", gioco);
			return "gioco.html";
		}
		
		return "giocoForm.html";
	}
	
	@PostMapping("/deleteGioco/{id}")
	public String deleteGioco(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		this.giocoService.deleteGioco(gioco);
		return "giochi.html";
	}
	
	@GetMapping("/giocoForm")
	public String getNewGioco(Model model) {
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
	
	@GetMapping("/modifyGiocoData/{id}")
	public String modifyGiocoData(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		model.addAttribute("gioco", gioco);
		return "modifyGiocoDataForm.html";
	}
	
}
