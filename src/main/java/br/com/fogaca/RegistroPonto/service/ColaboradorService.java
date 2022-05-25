package br.com.fogaca.RegistroPonto.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Colaborador;
import br.com.fogaca.RegistroPonto.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public List<Colaborador> list(){
		return colaboradorRepository.findAllByOrderByNome();
	}
	
	public Optional<Colaborador> findById(Long colaboradorId){
		return colaboradorRepository.findById(colaboradorId);
	}
	
	public void save(Colaborador colaborador) {
		colaboradorRepository.save(colaborador);
	}
	
	public void deleteById(Long id) {
		colaboradorRepository.deleteById(id);
	}

}
