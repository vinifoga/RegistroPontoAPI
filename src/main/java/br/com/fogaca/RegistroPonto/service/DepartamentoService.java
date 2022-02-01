package br.com.fogaca.RegistroPonto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Departamento;
import br.com.fogaca.RegistroPonto.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public List<Departamento> list(){
		return departamentoRepository.findAll();
	}

	public Optional<Departamento> findById(Long departamentoId) {
		return departamentoRepository.findById(departamentoId);

	}

	public void save(Departamento departamento) {
		departamentoRepository.save(departamento);
	}

	public void deleteById(Long id) {
		departamentoRepository.deleteById(id);
	}	
}
