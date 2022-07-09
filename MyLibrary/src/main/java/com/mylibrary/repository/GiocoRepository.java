package com.mylibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mylibrary.model.Gioco;

@Repository
public interface GiocoRepository extends CrudRepository<Gioco,Long> {
	
	public boolean existsByNomeAndGenere(String nome, String genere);
	
	@Modifying
    @Query("update Gioco g set g.nome=:nome, g.genere=:genere, g.descrizione=:descrizione where g.id=:id")
    public void updateGioco(@Param("nome") String nome, @Param("genere") String genere, @Param("descrizione") String descrizione, 
    					    @Param("id") Long id);
	
}
