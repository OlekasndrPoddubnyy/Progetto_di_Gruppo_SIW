package com.mylibrary.controller;


import javax.validation.Valid;

import com.mylibrary.controller.validator.SerieTvValidator;
import com.mylibrary.model.Commento;
import com.mylibrary.model.SerieTv;
import com.mylibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SerieTvController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private SerieTvService serieTvService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private GiocoService giocoService;

    @Autowired
    private SerieTvValidator serieTvValidator;
    @Autowired
    private CommentoService commentoService;


    @GetMapping("/serieTvForm")
    public String getSerieTvFormInserimento(Model model) {
        model.addAttribute("serieTv", new SerieTv());
        return "forms/serieTvForm";
    }

    @PostMapping("/admin/serieTv")
    public String saveSerieTv(@Valid @ModelAttribute("serieTv") SerieTv serieTv,
                              BindingResult bindingResult, Model model) {

        serieTvValidator.validate(serieTv, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.serieTvService.saveSerieTv(serieTv);
            model.addAttribute("films", filmService.findAllFilms());
            model.addAttribute("series", serieTvService.serieTvs());
            model.addAttribute("giochi", giocoService.findAllGiochi());
            model.addAttribute("libri", libroService.libri());
            return "admin/home";
        }
        return "forms/serieTvForm";
    }

    @GetMapping("/serieTv/{id}")
    public String getSerieTv(@PathVariable("id") long id, Model model) {

        model.addAttribute("serie", this.serieTvService.findById(id));
        return "serieTv";
    }

    @GetMapping("serieTv/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        this.serieTvService.deleteById(id);
        model.addAttribute("films", filmService.findAllFilms());
        model.addAttribute("series", serieTvService.serieTvs());
        model.addAttribute("giochi", giocoService.findAllGiochi());
        model.addAttribute("libri", libroService.libri());
        return "admin/home";
    }

    @GetMapping("/serieTvs")
    public String getAll( Model model) {

        model.addAttribute("serieTvs", this.serieTvService.serieTvs());
        return "index";
    }

    @GetMapping("/serieTvFormCerca")
    public String rimandaACerca(Model model) {
        model.addAttribute("serieTv", new SerieTv());
        return "serieTvFormCerca.html";
    }


    @GetMapping("/admin/updateSerieTVForm/{id}")
    public String modifySerieTVData(@PathVariable("id") Long id, Model model) {
        SerieTv serieTv = this.serieTvService.findById(id);
        model.addAttribute("serieTv", serieTv);
        return "forms/serieTvFormUpdate";
    }

    @PostMapping("/admin/updateSerieTV")
    public String modifySerieTVData(@Valid @ModelAttribute("serieTv") SerieTv serieTv, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            this.serieTvService.updateSerieTv(serieTv);
            model.addAttribute("films", filmService.findAllFilms());
            model.addAttribute("series", serieTvService.serieTvs());
            model.addAttribute("giochi", giocoService.findAllGiochi());
            model.addAttribute("libri", libroService.libri());
            return "admin/home";
        }
        model.addAttribute("serieTv", serieTv);
        return "forms/serieTvFormUpdate";
    }

    @GetMapping("/admin/episodi/{id}")
    public String listEpisodi(@PathVariable("id") Long id, Model model){
        model.addAttribute("serieTv", this.serieTvService.findById(id));
        return "modEpisodi";
    }


    @PostMapping("/serieTvCommento/{idS}/{username}")
    public String inserisciCommento(Model model, @PathVariable("username") String username, @PathVariable("idS") Long idS, @ModelAttribute("commento") Commento commento) {
        commento.setUsername(username);
        this.commentoService.save(commento);
        this.serieTvService.aggiungiCommentoASerieTv(idS, commento.getId());
        model.addAttribute("serie", this.serieTvService.findById(idS));
        return "serieTv";
    }




}
