package com.mylibrary.repository;

import com.mylibrary.model.Episodio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {

    @Modifying
    @Query(value = "delete from serie_tv_episodi stc where stc.episodi_id= :idE", nativeQuery = true)
    void eliminaEpisodioDaSerieTv(@Param("idE") Long idE);
}
