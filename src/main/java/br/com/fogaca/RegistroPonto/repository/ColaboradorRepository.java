package br.com.fogaca.RegistroPonto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fogaca.RegistroPonto.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	
	public List<Colaborador> findAllByOrderByNome();

}
