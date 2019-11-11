package com.factoring.app.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.factoring.app.exception.ResourceNotFoundException;
import com.factoring.app.model.Banco;
import com.factoring.app.repository.BancoRepository;
import com.factoring.app.service.BancoService;

@Service
public class BancoServiceImpl implements BancoService{


	@Autowired
	private BancoRepository bancoRepository;
	
	
	@Override
	public List<Banco> getAll() {
		List<Banco> bancos = new ArrayList<>();
		bancoRepository.findAll().iterator().forEachRemaining(bancos::add);
		return bancos;
	}

	@Override
	public Banco create(Banco object) {
		Banco newBanco;
		newBanco = bancoRepository.save(object);
		return newBanco;
	}

	@Override
	public Banco update(Long id, Banco objectupdate) {
		Banco banco = findById(id);

		banco.setNombre(objectupdate.getNombre());
		banco.setTasa(objectupdate.getTasa());
		banco.setTipoTasa(objectupdate.getTipoTasa());

		bancoRepository.save(banco);
		return banco;
	}

	@Override
	public void delete(Long objectId) {
		bancoRepository.delete(findById(objectId));
	}

	@Override
	public Banco findById(Long id) {
		Optional<Banco> banco = bancoRepository.findById(id);

		if (!banco.isPresent()) {
            throw new ResourceNotFoundException("There is no Banco with ID = " + id);
        }

		return banco.get();
	}

	@Override
	public Banco getLatestEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean BancoValid(Banco banco) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Page<Banco> findAll(Pageable pageable) {
		return bancoRepository.findAll(pageable);
	}
	
	
}
