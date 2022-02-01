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

import br.com.fogaca.RegistroPonto.controller.dto.ColaboradorDto;
import br.com.fogaca.RegistroPonto.controller.form.ColaboradorForm;
import br.com.fogaca.RegistroPonto.controller.form.ColaboradorUpdateForm;
import br.com.fogaca.RegistroPonto.model.Colaborador;
import br.com.fogaca.RegistroPonto.service.CargoService;
import br.com.fogaca.RegistroPonto.service.ColaboradorService;
import br.com.fogaca.RegistroPonto.service.UnidadeOrganizacionalService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private UnidadeOrganizacionalService unidadeOrganizacionalService;

	@GetMapping
	@Cacheable(value = "listaColaboradores")
	public List<Colaborador> list(Long id) {
		if (id == null) {
			return colaboradorService.list();
		} else {
			List<Colaborador> colaboradores = new ArrayList<>();
			colaboradores.add(colaboradorService.findById(id).get());
			return colaboradores;
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaColaboradores", allEntries = true)
	public ResponseEntity<ColaboradorDto> create(@RequestBody @Valid ColaboradorForm colaboradorForm,
			UriComponentsBuilder uriBuilder) {
		Colaborador colaborador = colaboradorForm.converter(cargoService, unidadeOrganizacionalService);
		colaboradorService.save(colaborador);
		URI uri = uriBuilder.path("/colaborador/{id}").buildAndExpand(colaborador.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(new ColaboradorDto(colaborador));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ColaboradorDto> findById(@PathVariable Long id) {
		if (colaboradorService.findById(id).isPresent()) {
			return ResponseEntity.ok(new ColaboradorDto(colaboradorService.findById(id).get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaColaboradores", allEntries = true)
	public ResponseEntity<ColaboradorDto> update(@PathVariable Long id,
			@RequestBody @Valid ColaboradorUpdateForm colaboradorForm) {
		if (colaboradorService.findById(id).isPresent()) {
			Colaborador colaborador = colaboradorForm.update(id, colaboradorService, cargoService, unidadeOrganizacionalService);
			return ResponseEntity.ok(new ColaboradorDto(colaborador));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaColaboradores", allEntries = true)
	public ResponseEntity<ColaboradorDto> delete(@PathVariable Long id){
		if(colaboradorService.findById(id).isPresent()) {
			colaboradorService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
