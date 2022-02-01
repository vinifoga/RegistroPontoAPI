package br.com.fogaca.RegistroPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fogaca.RegistroPonto.model.UnidadeOrganizacional;

@Repository
public interface UnidadeOrganizacionalRepository extends JpaRepository<UnidadeOrganizacional, Long>{

	
}
