package com.mylibrary.controller;

import com.mylibrary.controller.validator.EpisodioValidator;
import com.mylibrary.model.Episodio;
import com.mylibrary.model.SerieTv;
import com.mylibrary.service.EpisodioService;
import com.mylibrary.service.SerieTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.NULL;

@Controller
public class EpisodioController {

    @Autowired
    private EpisodioService episodioService;

    @Autowired
    private EpisodioValidator episodioValidator;

    @Autowired
    private SerieTvService serieTvService;


    @GetMapping("/episodioForm/{id}")
    public String getEpisodioFormInserimento( @PathVariable("id") Long idSTV, Model model) {
        model.addAttribute("episodio", new Episodio());
        model.addAttribute("serieTv", this.serieTvService.findById(idSTV));
        return "episodioForm";
    }

    @PostMapping("/admin/episodio/{id}")
    public String saveEpisodio(@Valid @ModelAttribute("episodio") Episodio episodio,
                              @PathVariable("id") Long id,
                              BindingResult bindingResult, Model model) {

        episodioValidator.validate(episodio, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.episodioService.salva(episodio);
            SerieTv serieTv = this.serieTvService.findById(id);
            serieTv.addEpisodio(episodio);
            if(episodio.getStagione() > serieTv.getNumeroStagioni())
                serieTv.setNumeroStagioni(episodio.getStagione());
            this.serieTvService.saveSerieTv(serieTv);
            model.addAttribute("serieTv", serieTv);
            return "modEpisodi";
        }
        return "episodioForm";
    }

    @GetMapping("episodio/{id}")
    public String getEpisodio(@PathVariable("id") long id, Model model) {

        model.addAttribute("episodio", this.episodioService.findById(id));
        return "episodio";
    }

    @GetMapping("/episodio/delete/{id}/{idS}")
    public String deleteById(@PathVariable("id") long id,
                             @PathVariable("idS") long ids, Model model) {
        this.serieTvService.deleteEpisodioId(id);
        this.episodioService.eliminaEpisodio(id);
        SerieTv serieTv = this.serieTvService.findById(ids);
        List<Long> listaId = new LinkedList<>();
        for(Episodio episodio : serieTv.getEpisodi())
            listaId.add(episodio.getId());
        int maxStagione= this.episodioService.maxStagione(listaId);

        if( serieTv.getNumeroStagioni() != maxStagione ) {
            this.serieTvService.updateNumStagioni(maxStagione, ids);
            serieTv.setNumeroStagioni(maxStagione);
        }

        model.addAttribute("serieTv", serieTv );

        return "modEpisodi";
    }

    @GetMapping("episodio/update/{id}/{idStv}")
    public String episodioUpdate(@PathVariable("id") Long id, @PathVariable("idStv") Long idStv, Model model) {
        model.addAttribute("serieTv", this.serieTvService.findById(idStv));
        model.addAttribute("episodio", this.episodioService.findById(id));
        return "episodioFormUpdate";
    }

    @PostMapping("/episodioFormUpdate/{id}")
    public String updateEpisodio(@Valid @ModelAttribute("episodio") Episodio episodio,
                               @PathVariable("id") Long id,
                               BindingResult bindingResult, Model model) {

        episodioValidator.validate(episodio, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.episodioService.salva(episodio);
            model.addAttribute("serieTv", this.serieTvService.findById(id));
            return "modEpisodi";
        }
        return "episodioFormUpdate";
    }
    
}
