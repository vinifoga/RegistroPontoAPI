package br.com.fogaca.RegistroPonto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.repository.RegistroRepository;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository registroRepository;
	
	public Page<Registro> list(Pageable paginacao){
		return registroRepository.findAll(paginacao);
	}
	
	public Optional<Registro> findById(Long registroId){
		return registroRepository.findById(registroId);
	}
	
	public void save(Registro registro) {
		registroRepository.save(registro);
	}
	
	public void deleteById(Long id) {
		registroRepository.deleteById(id);
	}

	public Page<Registro> findByColaborador_Matricula(Long colaboradorId, Pageable paginacao) {
		return registroRepository.findByColaborador_Matricula(colaboradorId, paginacao);
	}
}
