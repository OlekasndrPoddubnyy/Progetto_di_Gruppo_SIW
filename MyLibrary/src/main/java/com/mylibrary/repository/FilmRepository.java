package com.mylibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mylibrary.model.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film,Long> {
	
	public boolean existsByNomeAndAnnoAndGenere(String nome, Integer anno, String genere);
	
	@Modifying
    @Query("update Film f set f.nome=:nome, f.genere=:genere, f.anno=:anno, f.descrizione=:descrizione where f.id=:id")
    public void updateFilm(@Param("nome") String nome, @Param("genere") String genere, @Param("anno") Integer anno, 
    					   @Param("descrizione") String descrizione, @Param("id") Long id);

	@Modifying
	@Query(value = "insert into film_commenti (film_id, commenti_id) Values (:idF, :idC)", nativeQuery = true)
	void collegaFilmACommento(@Param("idF") Long idFilm, @Param("idC") Long idCommento);
	
}
