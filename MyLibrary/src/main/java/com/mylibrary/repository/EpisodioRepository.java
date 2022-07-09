package com.mylibrary.repository;

import com.mylibrary.model.Episodio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {

    @Query(value = "select max(e.stagione) from episodio e where e.id IN :listaId", nativeQuery = true)
    public int maxStagione(@Param("listaId")List<Long> listaId);


}
