package br.com.fogaca.RegistroPonto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Cargo;
import br.com.fogaca.RegistroPonto.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	public List<Cargo> list(){
		return cargoRepository.findAll();
	}

	public Optional<Cargo> findById(Long cargoId) {
		return cargoRepository.findById(cargoId);
	}

	public void save(Cargo cargo) {
		cargoRepository.save(cargo);
	}

	public void deleteById(Long id) {
		cargoRepository.deleteById(id);
	}


}
