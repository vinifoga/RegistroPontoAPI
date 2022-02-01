package br.com.fogaca.RegistroPonto.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fogaca.RegistroPonto.controller.dto.CargoDto;
import br.com.fogaca.RegistroPonto.controller.form.CargoForm;
import br.com.fogaca.RegistroPonto.controller.form.CargoUpdateForm;
import br.com.fogaca.RegistroPonto.model.Cargo;
import br.com.fogaca.RegistroPonto.service.CargoService;
import br.com.fogaca.RegistroPonto.service.DepartamentoService;

@RestController
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping
	@Cacheable(value = "listaCargos")
	public List<Cargo> list(Long id){
		if(id == null) {
			return cargoService.list();
		} else {
			List<Cargo> cargos = new ArrayList<>();
			cargos.add(cargoService.findById(id).get());
			return cargos;
		}		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaCargos", allEntries = true)
	public ResponseEntity<CargoDto> create(@RequestBody @Valid CargoForm cargoForm, UriComponentsBuilder uriBuilder) {
		Cargo cargo = cargoForm.converter(departamentoService);
		cargoService.save(cargo);
		URI uri = uriBuilder.path("/cargos/{id}").buildAndExpand(cargo.getId()).toUri();
		return ResponseEntity.created(uri).body(new CargoDto(cargo));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CargoDto> findById(@PathVariable Long id){
		if(cargoService.findById(id).isPresent()) {
			return ResponseEntity.ok(new CargoDto(cargoService.findById(id).get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaCargos", allEntries = true)
	public ResponseEntity<CargoDto> update(@PathVariable Long id, @RequestBody @Valid CargoUpdateForm cargoForm){
		if(cargoService.findById(id).isPresent()) {
			Cargo cargo = cargoForm.update(id, cargoService, departamentoService);
			return ResponseEntity.ok(new CargoDto(cargo));
		}
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaCargos", allEntries = true)
	public ResponseEntity<CargoDto> delete(@PathVariable Long id){
		if(cargoService.findById(id).isPresent()) {
			cargoService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
