package com.mylibrary.controller;

import com.mylibrary.controller.validator.EpisodioValidator;
import com.mylibrary.model.Episodio;
import com.mylibrary.model.SerieTv;
import com.mylibrary.service.EpisodioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EpisodioController {

    @Autowired
    private EpisodioService episodioService;

    @Autowired
    private EpisodioValidator episodioValidator;



    @GetMapping("/episodioForm")
    public String getEpisodioFormInserimento(Model model) {
        model.addAttribute("episodio", new Episodio());
        return "episodioForm";
    }

    @PostMapping("/admin/episodio")
    public String saveEpisodio(@Valid @ModelAttribute("episodio") Episodio episodio,
                              BindingResult bindingResult, Model model) {

        episodioValidator.validate(episodio, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.episodioService.salva(episodio);
            model.addAttribute("episodio", episodio);
            return "index";
        }
        return "episodioForm";
    }

    @GetMapping("episodio/{id}")
    public String getEpisodio(@PathVariable("id") long id, Model model) {

        model.addAttribute("episodio", this.episodioService.findById(id));
        return "episodio";
    }

    @GetMapping("episodio/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        this.episodioService.eliminaEpisodio(id);
        return "episodi";
    }

}
