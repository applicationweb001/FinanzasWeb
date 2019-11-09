package com.factoring.app.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.factoring.app.model.Recibo;
import com.factoring.app.repository.ReciboRepository;
import com.factoring.app.service.ReciboService;

@Service
public class ReciboServiceImpl implements ReciboService{

	@Autowired
	private ReciboRepository reciboRepository;
	
	@Override
	public List<Recibo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recibo create(Recibo object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recibo update(Long id, Recibo objectupdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long objectId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Recibo findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recibo getLatestEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Recibo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
