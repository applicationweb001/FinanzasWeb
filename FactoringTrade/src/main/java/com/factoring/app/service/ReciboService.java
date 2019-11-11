package com.factoring.app.service;

import com.factoring.app.model.Recibo;

public interface ReciboService extends CrudService<Recibo, Long>{

	boolean ReciboValid(Recibo recibo);

}
