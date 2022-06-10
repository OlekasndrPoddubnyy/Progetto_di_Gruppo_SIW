package mylibrary.controller;

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

import mylibrary.controller.validator.SerieTvValidator;
import mylibrary.model.SerieTv;
import mylibrary.service.SerieTvService;

@Controller
public class SerieTvController {
	
	@Autowired
	private SerieTvService serieTvService;
	
	

	@GetMapping("/serieTvFormInserimento")
	public String getSerieTvFormInserimento(Model model) {
		model.addAttribute("serieTv", new SerieTv());
		return "serieTvFormInserimento.html";
		}
	
	@PostMapping("serieTv/save")
	public String saveSerieTv(@Valid @ModelAttribute("serietv") SerieTv serieTv, 
			BindingResult bindingResult, Model model) {
		
		if(!bindingResult.hasErrors()) {
			this.serieTvService.saveSerieTv(serieTv);
			model.addAttribute("serieTv", serieTv);
			return "serietvVisualizza.html";
		}
		return "serieTvFormInserimento.html";
	}
	
	@GetMapping("serieTv/getSerie/{id}")
	public String getSerieTv(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("serieTv", this.serieTvService.findById(id));
		return "serieTvVisualizza.html";
	}
	
	@GetMapping("serieTv/delete/{id}")
	public String deleteById(@PathVariable("id") long id, Model model) {
		this.serieTvService.deleteById(id);
		return "ciao";
	}
	
	@GetMapping("serieTv/getByNome")
	public String getAllByName(@ModelAttribute("serieTv") SerieTv serieTv, Model model) {
		List<SerieTv> listaSerie = this.serieTvService.findAllByName(serieTv.getNome());
		model.addAttribute("serieTv", listaSerie);
		return "serieTvVisualizzaDaNome.html";
	}
	
	@GetMapping("/serieTvFormCerca")
	public String rimandaACerca(Model model) {
		model.addAttribute("serieTv", new SerieTv());
		return "serieTvFormCerca.html";
	}
	
	

}
