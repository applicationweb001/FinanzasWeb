package com.factoring.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.factoring.app.model.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario,Long>{

	/**
     * @return newest articleId
     */
    @Query(value = "SELECT MAX(id) FROM Usuario")
    Long findTopByOrderByIdDesc();

    /**
     * @param title     title of an article
     * @param author    author of an article
     * @return          List of articles with the same title and author
     */
    //title+author must be unique
    @Query("SELECT a FROM Usuario a WHERE a.nombre=:nombre")
    List<Usuario> findByName( @Param("nombre") String nombre);


	/**
     * @param pageable
     * @return          a page of entities that fulfill the restrictions
     *                  specified by the Pageable object
     */
    Page<Usuario> findAll(Pageable pageable);

}
