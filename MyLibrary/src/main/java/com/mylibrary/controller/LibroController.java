package com.mylibrary.controller;

import com.mylibrary.controller.validator.LibroValidator;
import com.mylibrary.model.Libro;
import com.mylibrary.service.LibroService;
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
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroValidator libroValidator;


    @GetMapping("/libroForm")
    public String getLibroFormInserimento(Model model) {
        model.addAttribute("libro", new Libro());
        return "libroForm";
    }

    @PostMapping("/admin/libro")
    public String saveLibro(@Valid @ModelAttribute("libro") Libro libro,
                              BindingResult bindingResult, Model model) {

        libroValidator.validate(libro, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.libroService.salva(libro);
            model.addAttribute("libro", libro);
            return "index";
        }
        return "libroForm";
    }

    @GetMapping("libro/{id}")
    public String getLibro(@PathVariable("id") long id, Model model) {

        model.addAttribute("libro", this.libroService.findById(id));
        return "libro";
    }

    @GetMapping("libro/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        this.libroService.eliminaLibro(id);
        return "libri";
    }
}
