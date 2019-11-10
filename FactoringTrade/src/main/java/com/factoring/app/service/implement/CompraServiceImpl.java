package com.factoring.app.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.factoring.app.model.Compra;
import com.factoring.app.repository.CompraRepository;
import com.factoring.app.service.CompraService;



@Service
public class CompraServiceImpl implements CompraService{
	@Autowired
	private CompraRepository compraRepository;
	
	@Override
	public List<Compra> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra create(Compra object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra update(Long id, Compra objectupdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long objectId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compra findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra getLatestEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Compra> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
