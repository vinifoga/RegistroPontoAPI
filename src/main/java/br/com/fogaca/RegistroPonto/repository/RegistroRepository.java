package br.com.fogaca.RegistroPonto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fogaca.RegistroPonto.model.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{

	Page<Registro> findByColaborador_Matricula(Long colaboradorId, Pageable paginacao);
	List<Registro> findByColaborador_Matricula(Long colaboradorId);
	Page<Registro> findByDataAndColaborador_Matricula(LocalDate data, Long colaboradorId, Pageable paginacao);
	
	@Query(value = "select * from registro where colaborador_matricula = :colaboradorId and data between :dataInicio and :dataFim", nativeQuery = true)
	List<Registro> findByIntervaloAndMatricula(LocalDate dataInicio, LocalDate dataFim, Long colaboradorId);
	
	@Query(value = "select * from registro where status != 'NORMAL'")
	List<Registro> findNaoNormal();
}
