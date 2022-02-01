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

import br.com.fogaca.RegistroPonto.controller.dto.DepartamentoDto;
import br.com.fogaca.RegistroPonto.controller.form.DepartamentoForm;
import br.com.fogaca.RegistroPonto.controller.form.DepartamentoUpdateForm;
import br.com.fogaca.RegistroPonto.model.Departamento;
import br.com.fogaca.RegistroPonto.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping
	@Cacheable(value = "listaDepartamentos")
	public List<Departamento> list(Long id){
		if(id == null) {
			return departamentoService.list();
		} else {
			List<Departamento> departamentos = new ArrayList<>();
			departamentos.add(departamentoService.findById(id).get());
			return departamentos;
		}		
	}	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDepartamentos", allEntries = true)
	public ResponseEntity<DepartamentoDto> create(@RequestBody @Valid DepartamentoForm departamentoForm, UriComponentsBuilder uriBuilder) {
		Departamento departamento = departamentoForm.converter();
		departamentoService.save(departamento);
		URI uri = uriBuilder.path("/departamentos/{id}").buildAndExpand(departamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new DepartamentoDto(departamento));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartamentoDto> findById(@PathVariable Long id) {
		if(departamentoService.findById(id).isPresent()) {
			return ResponseEntity.ok(new DepartamentoDto(departamentoService.findById(id).get()));
		} 
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDepartamentos", allEntries = true)
	public ResponseEntity<DepartamentoDto> update(@PathVariable Long id, @RequestBody @Valid DepartamentoUpdateForm departamentoForm){
		if(departamentoService.findById(id).isPresent()) {
			Departamento departamento = departamentoForm.update(id, departamentoService);
			return ResponseEntity.ok(new DepartamentoDto(departamento));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDepartamentos", allEntries = true)
	public ResponseEntity<DepartamentoDto> delete(@PathVariable Long id) {
		if (departamentoService.findById(id).isPresent()) {
			departamentoService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}
}
