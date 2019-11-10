package com.factoring.app.service.implement;
import com.factoring.app.exception.ResourceNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<Compra> compras = new ArrayList<>();
		compraRepository.findAll().iterator().forEachRemaining(compras::add);
		return compras;
	}

	@Override
	public Compra create(Compra object) {
		Compra newcompra;
		newcompra = compraRepository.save(object);
		return newcompra;
	}

	@Override
	public Compra update(Long id, Compra objectupdate) {
		Compra compra = findById(id);
		
		compra.setFechaDescuento(objectupdate.getFechaDescuento());
		compra.setTep(objectupdate.getTep());
		compra.setPeriodo(objectupdate.getPeriodo());
		compra.setTipoMoneda(objectupdate.getTipoMoneda());
		compraRepository.save(compra);	
		return compra;
		
	}

	@Override
	public void delete(Long objectId) {
		compraRepository.delete(findById(objectId));
		
	}

	@Override
	public Compra findById(Long id) {
		Optional<Compra> compra = compraRepository.findById(id);

		if (!compra.isPresent()) {
           throw new ResourceNotFoundException("There is no Compra with ID = " + id);
        }

		return compra.get();
	}

	@Override
	public Compra getLatestEntry() {
		 List<Compra> compra = getAll();
	        if(compra.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestcompraID = compraRepository.findTopByOrderByIdDesc();
	            return findById(latestcompraID);
	        }
	}

	@Override
	public Page<Compra> findAll(Pageable pageable) {
		return compraRepository.findAll(pageable);
	}

	@Override
	public boolean CompraValid(Compra compra) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//@Override
	//public boolean CompraValid(Compra compra) {
		//List<Compra> compras= new ArrayList<>();
		//compraRepository.findByCompraId(compra.getId()).iterator().forEachRemaining(compras::add);
        //if (!compras.isEmpty()) { return false;}
        //else {return true;}
	//}

}
