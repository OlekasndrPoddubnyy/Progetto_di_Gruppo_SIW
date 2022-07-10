package com.mylibrary.service;

import com.mylibrary.model.SerieTv;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import com.mylibrary.repository.SerieTvRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieTvService {

    @Autowired
    private SerieTvRepository serieTvRepository;

    @Transactional
    public void saveSerieTv(SerieTv serieTv) {
        this.serieTvRepository.save(serieTv);
    }
    
    @Transactional
    public void deleteById(Long id) {
    	this.serieTvRepository.deleteSerieTvPreferita(id);
        this.serieTvRepository.deleteById(id);
    }
    
    public List<SerieTv> findAllByName(String nome){
        return this.serieTvRepository.findAllByNome(nome);
    }

    public SerieTv findById(long id) {
        return this.serieTvRepository.findById(id);
    }

    public List<SerieTv> serieTvs() { return this.serieTvRepository.findAll(); }

    @Transactional
    public  void deleteEpisodioId(long id) { this.serieTvRepository.deleteEpisodioId(id);}
    
    @Transactional
    public void updateSerieTv(SerieTv editedSerieTv) {
    	this.serieTvRepository.updateSerieTv(editedSerieTv.getNome(), editedSerieTv.getGenere(), editedSerieTv.getNumeroStagioni(), editedSerieTv.getDescrizione(), editedSerieTv.getId());
    }

    @Transactional
    public void updateNumStagioni(int numStagioni, Long id) {
        this.serieTvRepository.updateNumStagioni(numStagioni, id);
    }

    @Transactional
    public void aggiungiCommentoASerieTv(Long idSerieTv, Long idCommento) {
        this.serieTvRepository.collegaSerieTvACommento(idSerieTv, idCommento);
    }

    
}
