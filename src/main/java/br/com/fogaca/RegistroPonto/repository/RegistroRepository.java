package br.com.fogaca.RegistroPonto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fogaca.RegistroPonto.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{

	Page<Registro> findByColaborador_Matricula(Long colaboradorId, Pageable paginacao);
}
