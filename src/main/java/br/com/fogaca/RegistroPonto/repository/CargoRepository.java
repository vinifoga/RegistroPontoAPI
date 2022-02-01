package br.com.fogaca.RegistroPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fogaca.RegistroPonto.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
