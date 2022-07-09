package com.mylibrary.service;

import com.mylibrary.model.Episodio;
import com.mylibrary.repository.EpisodioRepository;
import com.mylibrary.repository.SerieTvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodioService {

    @Autowired
    private EpisodioRepository episodioRepository;

    @Autowired
    private SerieTvRepository serieTvRepository;

    public void salva(Episodio episodio){
        this.episodioRepository.save(episodio);
    }

    public Episodio findById(Long id) {
        return this.episodioRepository.findById(id).get();
    }

    public void eliminaEpisodio(Long id) {
        this.episodioRepository.deleteById(id);
    }

}
