package br.com.fogaca.RegistroPonto.service;

import java.time.LocalDate;
import java.util.List;
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
	
	public List<Registro> findByColaborador_Matricula(Long colaboradorId){
		return registroRepository.findByColaborador_Matricula(colaboradorId);
	}
	
	public Page<Registro> findByDataAndColaborador_Matricula(LocalDate data, Long colaboradorId, Pageable paginacao){
		return registroRepository.findByDataAndColaborador_Matricula(data, colaboradorId, paginacao);
	}
	
	public List<Registro> findByIntervaloAndMatricula(LocalDate dataInicio, LocalDate dataFim, Long colaboradorId){
		return registroRepository.findByIntervaloAndMatricula(dataInicio, dataFim, colaboradorId);
	}
	
	public List<Registro> findRegistroNaoNormal(){
		return registroRepository.findNaoNormal();
	}
}
