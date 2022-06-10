package mylibrary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mylibrary.model.Episodio;
import mylibrary.model.SerieTv;
import mylibrary.service.EpisodioService;

@Controller
public class EpisodioController {

	@Autowired
	private EpisodioService episodioService;

	@PostMapping("/episodio/save")
	public String salvaEpisodio(@Valid @ModelAttribute("episodio") Episodio episodio, 
			BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			this.episodioService.saveEpisodio(episodio);
			model.addAttribute("episodio", episodio);
			return "episodioVisualizza.html";
		}
		return "ciao";
	}
	
	@GetMapping("/episodioFormInserimento")
	public String episodioTvFormInserimento(Model model) {
		model.addAttribute("episodio", new Episodio());
		return "ciao";
		}

	@GetMapping("/episodio/findbynome/{id}")
	public String findByTitolo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("episodio", this.episodioService.findById(id));
		return "ciao";

	}
	
	@GetMapping("/episodio/deletebyid/{id}")
	public String deleteById(@PathVariable("id") Long id, Model model) {
		this.episodioService.deleteById(id);
		return "ciao";
	}

}
