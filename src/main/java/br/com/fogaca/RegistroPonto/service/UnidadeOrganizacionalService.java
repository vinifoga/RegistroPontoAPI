package br.com.fogaca.RegistroPonto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.UnidadeOrganizacional;
import br.com.fogaca.RegistroPonto.repository.UnidadeOrganizacionalRepository;

@Service
public class UnidadeOrganizacionalService {
	
	@Autowired
	private UnidadeOrganizacionalRepository unidadeOrganizacionalRepository;
	
	public List<UnidadeOrganizacional> list(){
		return unidadeOrganizacionalRepository.findAll();
	}
	
	public Optional<UnidadeOrganizacional> findById(Long unidadeId){
		return unidadeOrganizacionalRepository.findById(unidadeId);
	}
	
	public void save(UnidadeOrganizacional unidadeOrganizacional) {
		unidadeOrganizacionalRepository.save(unidadeOrganizacional);
	}
	
	public void deleteById(Long id) {
		unidadeOrganizacionalRepository.deleteById(id);
	}

}
