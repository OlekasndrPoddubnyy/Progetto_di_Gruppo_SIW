package com.mylibrary.repository;

import com.mylibrary.model.Libro;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    @Modifying
    @Query(value = "delete from libro_commenti stc where stc.libro_id= :idL", nativeQuery = true)
    void eliminaCommentiLibro(@Param("idS") Long idL);
}
