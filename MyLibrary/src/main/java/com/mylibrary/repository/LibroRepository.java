package com.mylibrary.repository;

import com.mylibrary.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {

}
