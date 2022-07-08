package com.mylibrary.repository;

import com.mylibrary.model.Episodio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EpisodioRepository extends CrudRepository<Episodio, Long> {


}
