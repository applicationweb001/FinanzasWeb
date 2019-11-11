package com.factoring.app.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.factoring.app.exception.ResourceNotFoundException;
import com.factoring.app.model.Recibo;
import com.factoring.app.repository.ReciboRepository;
import com.factoring.app.service.ReciboService;

@Service
public class ReciboServiceImpl implements ReciboService {

	@Autowired
	private ReciboRepository reciboRepository;

	@Override
	public List<Recibo> getAll() {
		List<Recibo> recibos = new ArrayList<>();
		reciboRepository.findAll().iterator().forEachRemaining(recibos::add);
		return recibos;
	}

	@Override
	public Recibo create(Recibo object) {
		Recibo newRecibo;
		newRecibo = reciboRepository.save(object);
		return newRecibo;
	}

	@Override
	public Recibo update(Long id, Recibo objectupdate) {
		Recibo recibo = findById(id);

		recibo.setBanco(objectupdate.getBanco());
		recibo.setFechaEmision(objectupdate.getFechaEmision());
		recibo.setFechaPago(objectupdate.getFechaPago());
		recibo.setRetencion(objectupdate.getRetencion());
		recibo.setTotalPago(objectupdate.getTotalPago());

		reciboRepository.save(objectupdate);
		return recibo;
	}

	@Override
	public void delete(Long objectId) {
		reciboRepository.delete(findById(objectId));
	}

	@Override
	public Recibo findById(Long id) {
		Optional<Recibo> recibo= reciboRepository.findById(id);

		if (!recibo.isPresent()) {
            throw new ResourceNotFoundException("There is no Recibo with ID = " + id);
        }
		return recibo.get();
	}

	@Override
	public Recibo getLatestEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	 //Pagination
	@Override
	public Page<Recibo> findAll(Pageable pageable) {
        return reciboRepository.findAll(pageable);

	}

	@Override
	public boolean ReciboValid(Recibo recibo) {
		// TODO Auto-generated method stub
		return false;
	}

}
