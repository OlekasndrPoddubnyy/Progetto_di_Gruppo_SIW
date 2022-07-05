package com.mylibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylibrary.model.Gioco;
import com.mylibrary.repository.GiocoRepository;

@Service
public class GiocoService {
	
	@Autowired
	private GiocoRepository giocoRepository;

	@Transactional
	public void save(Gioco gioco) {
		this.giocoRepository.save(gioco);
	}

	public Gioco findGiocoById(Long id) {
		return this.giocoRepository.findById(id).get();
	}

	public List<Gioco> findAllGiochi() {
		List<Gioco> giochi = new ArrayList<>();

		for(Gioco gioco : this.giocoRepository.findAll()) {
			giochi.add(gioco);
		}

		return giochi;
	}

	@Transactional
	public void deleteGioco(Gioco gioco) {
		this.giocoRepository.delete(gioco);
	}
	
	public boolean alreadyExists(Gioco gioco) {
		return this.giocoRepository.existsByNomeAndGenere(gioco.getNome(), gioco.getGenere());
	}
	
}
