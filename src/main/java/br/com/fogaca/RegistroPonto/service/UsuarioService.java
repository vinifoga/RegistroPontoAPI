package br.com.fogaca.RegistroPonto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Usuario;
import br.com.fogaca.RegistroPonto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> list(){
		return usuarioRepository.findByOrderByNome();
	}

	public Optional<Usuario> findById(Long usuarioId) {
		return usuarioRepository.findById(usuarioId);
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
}
