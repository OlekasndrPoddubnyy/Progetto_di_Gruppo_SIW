package com.mylibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mylibrary.model.SerieTv;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieTvRepository extends CrudRepository<SerieTv, Long> {

    public boolean existsById(long id);
    public void deleteById(long id);
    public List<SerieTv> findAllByNome(String nome);
    public SerieTv findById(long id);

    public List<SerieTv> findAll();

    @Modifying
    @Query(value = "delete from serie_tv_episodi where episodi_id=:idE", nativeQuery = true)
    public void deleteEpisodioId(@Param("idE")Long idE);
    
    @Modifying
    @Query("update SerieTv s set s.nome=:nome, s.genere=:genere, s.numeroStagioni=:stagioni, s.descrizione=:descrizione where s.id=:id")
    public void updateSerieTv(@Param("nome") String nome, @Param("genere") String genere, @Param("stagioni") Integer numeroStagioni, 
    						  @Param("descrizione") String descrizione, @Param("id") Long id);
    
}