package com.factoring.app.service;

import com.factoring.app.model.Compra;

public interface CompraService extends CrudService<Compra, Long>{

	boolean CompraValid(Compra compra);
	
}

