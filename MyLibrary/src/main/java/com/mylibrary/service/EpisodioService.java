package com.mylibrary.service;

import com.mylibrary.model.Episodio;
import com.mylibrary.repository.EpisodioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodioService {

    @Autowired
    private EpisodioRepository episodioRepository;

    public void salva(Episodio episodio){
        this.episodioRepository.save(episodio);
    }

    public Episodio findById(Long id) {
        return this.episodioRepository.findById(id).get();
    }

    public void deleteEpisodioDaSerieTv(Long idE) {
        this.episodioRepository.eliminaEpisodioDaSerieTv(idE);
    }

    public void deleteEpisodio(Long idE) {
        this.episodioRepository.deleteById(idE);
    }

}
