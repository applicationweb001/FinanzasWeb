package com.factoring.app.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.factoring.app.exception.ResourceNotFoundException;

import com.factoring.app.model.Usuario;
import com.factoring.app.repository.UsuarioRepository;
import com.factoring.app.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	
	public List<Usuario> getAll() {
		List<Usuario> usuraios = new ArrayList<>();
		usuarioRepository.findAll().iterator().forEachRemaining(usuraios::add);
		return usuraios;
	}

	@Override
	public Usuario create(Usuario object) {
		Usuario newUsuario;
		newUsuario = usuarioRepository.save(object);
		return newUsuario;
	}

	@Override
	public Usuario update(Long id, Usuario objectupdate) {
		Usuario usuario = findById(id);

		usuario.setNombre(objectupdate.getNombre());
		usuario.setRUC(objectupdate.getRUC());
		usuario.setApellido(objectupdate.getApellido());

		usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public void delete(Long objectId) {
		usuarioRepository.delete(findById(objectId));
		
	}

	@Override
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
            throw new ResourceNotFoundException("There is no Usuario with ID = " + id);
        }

		return usuario.get();

	}

	@Override
	public Usuario getLatestEntry() {
		List<Usuario> usuarios = getAll();
        if(usuarios.isEmpty()){
            return null;
        }
        else{
            Long latestUsuarioID = usuarioRepository.findTopByOrderByIdDesc();
            return findById(latestUsuarioID);
        }
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	
	
}
