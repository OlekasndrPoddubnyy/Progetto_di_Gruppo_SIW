package com.mylibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mylibrary.model.Commento;

@Repository
public interface CommentoRepository extends CrudRepository<Commento,Long> {
	
}
