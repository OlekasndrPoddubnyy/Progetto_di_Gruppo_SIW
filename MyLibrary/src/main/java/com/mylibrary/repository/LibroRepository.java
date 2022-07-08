package com.mylibrary.repository;

import com.mylibrary.model.Libro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, Long> {


    public List<Libro> findAll();

}
