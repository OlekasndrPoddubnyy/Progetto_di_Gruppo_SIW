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


    

    @GetMapping("/serieTvForm")
    public String getSerieTvFormInserimento(Model model) {
        model.addAttribute("serieTv", new SerieTv());
        return "serieTvForm";
    }

    @PostMapping("/admin/serieTv")
    public String saveSerieTv(@Valid @ModelAttribute("serieTv") SerieTv serieTv,
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
        return "serieTvFormUpdate.html";
    }

    @PostMapping("/admin/updateSerieTV")
    public String modifySerieTVData(@Valid @ModelAttribute("serieTv") SerieTv serieTv, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            this.serieTvService.saveSerieTv(serieTv);
            model.addAttribute("serieTVs", this.serieTvService.serieTvs());
            return "admin/home.html";
        }

        return "serieTvFormUpdate.html";
    }




}
