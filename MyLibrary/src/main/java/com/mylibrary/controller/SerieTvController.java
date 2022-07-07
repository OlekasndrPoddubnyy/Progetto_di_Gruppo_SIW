package com.mylibrary.controller;

import java.util.List;

import javax.validation.Valid;

import com.mylibrary.controller.validator.SerieTvValidator;
import com.mylibrary.model.SerieTv;
import com.mylibrary.service.SerieTvService;
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
    private SerieTvService serieTvService;

    @Autowired
    private SerieTvValidator serieTvValidator;


    

    @GetMapping("/episodioForm")
    public String getSerieTvFormInserimento(Model model) {
        model.addAttribute("serieTv", new SerieTv());
        return "serieTvForm";
    }

    @PostMapping("/admin/serieTv")
    public String saveSerieTv(@Valid @ModelAttribute("serietv") SerieTv serieTv,
                              BindingResult bindingResult, Model model) {

        serieTvValidator.validate(serieTv, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.serieTvService.saveSerieTv(serieTv);
            model.addAttribute("serieTv", serieTv);
            return "index";
        }
        return "serieTvForm";
    }

    @GetMapping("serieTv/{id}")
    public String getSerieTv(@PathVariable("id") long id, Model model) {

        model.addAttribute("serieTv", this.serieTvService.findById(id));
        return "serieTv";
    }

    @GetMapping("serieTv/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        this.serieTvService.deleteById(id);
        return "serieTvs";
    }

    @GetMapping("/serieTv")
    public String getAllByName(@ModelAttribute("serieTv") SerieTv serieTv, Model model) {
        List<SerieTv> listaSerie = this.serieTvService.findAllByName(serieTv.getNome());
        model.addAttribute("serieTv", listaSerie);
        return "index";
    }

    @GetMapping("/serieTvFormCerca")
    public String rimandaACerca(Model model) {
        model.addAttribute("serieTv", new SerieTv());
        return "serieTvFormCerca.html";
    }



}
