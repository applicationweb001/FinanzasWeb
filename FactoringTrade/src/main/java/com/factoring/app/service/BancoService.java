package com.factoring.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.factoring.app.model.Banco;

public interface BancoService extends CrudService<Banco,Long>{
	Banco findById(Long id);
	
	/**
     * @return newest banco
     */
    Banco getLatestEntry();

    /**
     * tests whether there is an banco with te same title and author in the database
     * @param article
     * @return true if there is no banco with the same author and title in the database
     */
    boolean BancoValid(Banco banco);

	

	//Pagination
    Page<Banco> findAll(Pageable pageable);
}
