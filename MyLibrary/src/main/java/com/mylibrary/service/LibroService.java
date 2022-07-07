package com.mylibrary.service;

import com.mylibrary.model.Libro;
import com.mylibrary.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public void salva(Libro libro) {
        this.libroRepository.save(libro);
    }

    public void eliminaLibro(Long idL) {
        this.libroRepository.deleteById(idL);
    }

    public Libro findById(Long id) {
        return this.libroRepository.findById(id).get();
    }



}
