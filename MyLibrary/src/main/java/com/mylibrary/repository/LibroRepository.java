package com.mylibrary.repository;

import com.mylibrary.model.Libro;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, Long> {
	
    public List<Libro> findAll();
    
    @Modifying
    @Query("update Libro l set l.nome=:nome, l.genere=:genere, l.autore=:autore, l.descrizione=:descrizione where l.id=:id")
    public void updateLibro(@Param("nome") String nome, @Param("genere") String genere, @Param("autore") String autore, 
    					    @Param("descrizione") String descrizione, @Param("id") Long id);
    
}
