package com.mylibrary.repository;

import com.mylibrary.model.Libro;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends CrudRepository<Libro, Long> {


}
