package com.factoring.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.factoring.app.model.Banco;


public interface BancoRepository extends PagingAndSortingRepository<Banco, Long>{

	@Query(value = "SELECT MAX(id) FROM Banco")
    Long findTopByOrderByIdDesc();
	
	@Query("SELECT b FROM Banco b WHERE b.nombre =:nombre ")
    List<Banco> findByName(@Param("nombre") String nombre);


    Page<Banco> findAll(Pageable pageable);
	
}
