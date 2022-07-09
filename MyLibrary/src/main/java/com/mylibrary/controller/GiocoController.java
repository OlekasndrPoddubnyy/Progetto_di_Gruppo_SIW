package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import com.mylibrary.service.FilmService;
import com.mylibrary.service.LibroService;
import com.mylibrary.service.SerieTvService;
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
	private FilmService filmService;

	@Autowired
	private SerieTvService serieTvService;

	@Autowired
	private LibroService libroService;

	@Autowired
	private GiocoService giocoService;
	
	@Autowired
	private GiocoValidator giocoValidator;
	
	@PostMapping("/gioco")
	public String addGioco(@Valid @ModelAttribute("gioco") Gioco gioco, BindingResult bindingResult, Model model) {
		this.giocoValidator.validate(gioco, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.giocoService.save(gioco);
			model.addAttribute("films", filmService.findAllFilms());
			model.addAttribute("series", serieTvService.serieTvs());
			model.addAttribute("giochi", giocoService.findAllGiochi());
			model.addAttribute("libri", libroService.libri());
			return "admin/home";
		}
		
		return "giocoForm.html";
	}
	
	@GetMapping("/deleteGioco/{id}")
	public String deleteGioco(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		this.giocoService.deleteGioco(gioco);
		model.addAttribute("films", filmService.findAllFilms());
		model.addAttribute("series", serieTvService.serieTvs());
		model.addAttribute("giochi", giocoService.findAllGiochi());
		model.addAttribute("libri", libroService.libri());
		return "admin/home";
	}
	
	@GetMapping("/giocoForm")
	public String getNewGioco(Model model) {
		model.addAttribute("gioco", new Gioco());
		return "giocoForm.html";
	}
	
	@GetMapping("/gioco/{id}")
	public String getGioco(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		model.addAttribute("gioco", gioco);
		return "gioco";
	}
	
	@GetMapping("/giochi")
	public String getAllGiochi(Model model) {
		List<Gioco> giochi = this.giocoService.findAllGiochi();
		model.addAttribute("giochi", giochi);
		return "giochi.html";
	}
	
	@GetMapping("/admin/updateGiocoForm/{id}")
	public String modifyGiocoData(@PathVariable("id") Long id, Model model) {
		Gioco gioco = this.giocoService.findGiocoById(id);
		model.addAttribute("gioco", gioco);
		return "giocoFormUpdate.html";
	}
	
	@PostMapping("/admin/updateGioco")
	public String modifyGiocoData(@Valid @ModelAttribute("gioco") Gioco gioco, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.giocoService.updateGioco(gioco);
			model.addAttribute("films", filmService.findAllFilms());
			model.addAttribute("series", serieTvService.serieTvs());
			model.addAttribute("giochi", giocoService.findAllGiochi());
			model.addAttribute("libri", libroService.libri());
			return "admin/home";
		}
		model.addAttribute("gioco", gioco);
		return "giocoFormUpdate.html";
	}
	
}
