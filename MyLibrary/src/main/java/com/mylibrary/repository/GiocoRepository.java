package com.mylibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mylibrary.model.Gioco;

@Repository
public interface GiocoRepository extends CrudRepository<Gioco,Long> {
	
}
