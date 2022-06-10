package com.mylibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylibrary.model.Commento;
import com.mylibrary.repository.CommentoRepository;

@Service
public class CommentoService {
	
	@Autowired
	private CommentoRepository commentoRepository;
	
	@Transactional
	public void save(Commento commento) {
		this.commentoRepository.save(commento);
	}
	
	public List<Commento> findAllCommenti() {
		List<Commento> commenti = new ArrayList<>();
		
		for(Commento commento : this.commentoRepository.findAll()) {
			commenti.add(commento);
		}
		
		return commenti;
	}
	
	public Commento findCommentoById(Long id) {
		return this.commentoRepository.findById(id).get();
	}
	
}
