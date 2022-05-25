package com.mylibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mylibrary.model.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {
	
	public boolean existsByNomeAndAnno(String nome, String anno);
	
}