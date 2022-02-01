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

import br.com.fogaca.RegistroPonto.controller.dto.UnidadeOrganizacionalDto;
import br.com.fogaca.RegistroPonto.controller.form.UnidadeOrganizacionalForm;
import br.com.fogaca.RegistroPonto.controller.form.UnidadeOrganizacionalUpdateForm;
import br.com.fogaca.RegistroPonto.model.UnidadeOrganizacional;
import br.com.fogaca.RegistroPonto.service.UnidadeOrganizacionalService;

@RestController
@RequestMapping("/unidades")
public class UnidadeOrganizacionalController {

	@Autowired
	private UnidadeOrganizacionalService unidadeOrganizacionalService;
	
	@GetMapping
	@Cacheable(value = "listaUnidades")
	public List<UnidadeOrganizacional> list(Long id){
		if(id == null) {
			return unidadeOrganizacionalService.list();
		} else {
			List<UnidadeOrganizacional> unidades = new ArrayList<>();
			unidades.add(unidadeOrganizacionalService.findById(id).get());
			return unidades;		
		}
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaUnidades", allEntries = true)
	public ResponseEntity<UnidadeOrganizacionalDto> create(@RequestBody @Valid UnidadeOrganizacionalForm unidadeOrganizacionalForm, UriComponentsBuilder uriBuilder){
		UnidadeOrganizacional unidade = unidadeOrganizacionalForm.converter();
		unidadeOrganizacionalService.save(unidade);
		URI uri = uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getCodUnidade()).toUri();
		return ResponseEntity.created(uri).body(new UnidadeOrganizacionalDto(unidade));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnidadeOrganizacionalDto> findById(@PathVariable Long id){
		if(unidadeOrganizacionalService.findById(id).isPresent()) {
			return ResponseEntity.ok(new UnidadeOrganizacionalDto(unidadeOrganizacionalService.findById(id).get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaUnidades", allEntries = true)
	public ResponseEntity<UnidadeOrganizacionalDto> update(@PathVariable Long id, @RequestBody @Valid UnidadeOrganizacionalUpdateForm unidadeForm){
		if(unidadeOrganizacionalService.findById(id).isPresent()){
			UnidadeOrganizacional unidade = unidadeForm.update(id, unidadeOrganizacionalService);
			return ResponseEntity.ok(new UnidadeOrganizacionalDto(unidade));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaUnidades", allEntries = true)
	public ResponseEntity<UnidadeOrganizacionalDto> delete(@PathVariable Long id){
		if(unidadeOrganizacionalService.findById(id).isPresent()) {
			unidadeOrganizacionalService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
