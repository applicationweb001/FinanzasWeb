package com.factoring.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.factoring.app.model.Recibo;

@Repository
public interface ReciboRepository extends PagingAndSortingRepository<Recibo, Long>{

	/**
     * @return newest articleId
     */
    @Query(value = "SELECT MAX(id) FROM Recibo")
    Long findTopByOrderByIdDesc();

    /**
     * @param title     title of an article
     * @param author    author of an article
     * @return          List of articles with the same title and author
     */
    //Buscar los recibos de un usuario
    //@Query("SELECT r FROM Recibo r WHERE r.FK_usuario=:user")
    //List<Recibo> findByReciboId(@Param("user") Long id);

	/**
     * @param pageable
     * @return          a page of entities that fulfill the restrictions
     *                  specified by the Pageable object
     */
    Page<Recibo> findAll(Pageable pageable);
	
	
}
