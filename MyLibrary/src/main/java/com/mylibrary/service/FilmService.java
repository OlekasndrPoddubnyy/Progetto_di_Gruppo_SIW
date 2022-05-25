package com.mylibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylibrary.model.Film;
import com.mylibrary.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Transactional
	public void save(Film film) {
		this.filmRepository.save(film);
	}
	
	public Film findFilmById(Long id) {
		return this.filmRepository.findById(id).get();
	}
	
	public List<Film> findAllFilms() {
		List<Film> films = new ArrayList<>();
		
		for(Film film : this.filmRepository.findAll()) {
			films.add(film);
		}
		
		return films;
	}
	
	@Transactional
	public void deleteFilm(Film film) {
		this.filmRepository.delete(film);
	}
	
	public boolean alreadyExists(Film film) {
		return this.filmRepository.existsByNomeAndAnno(film.getNome(), film.getAnno());
	}
	
}
