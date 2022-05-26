package br.com.fogaca.RegistroPonto.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fogaca.RegistroPonto.controller.dto.RegistroDto;
import br.com.fogaca.RegistroPonto.controller.form.RegistroForm;
import br.com.fogaca.RegistroPonto.controller.form.RegistroUpdateForm;
import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.service.ColaboradorService;
import br.com.fogaca.RegistroPonto.service.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping("/{colaboradorId}/{data}")
	public Page<RegistroDto> list(Long id, @PathVariable(required = false)  Long colaboradorId, @PathVariable(required = false) String data, @PageableDefault(sort= {"data","hora"}, direction = Direction.DESC, page = 0, size = 20) Pageable paginacao){
		
		if(colaboradorId == null) { 
			Page<Registro> registros = registroService.list(paginacao);
			return RegistroDto.converterRegistro(registros);
		} else if(data == null || data.isEmpty() || data.compareTo("99")==0) {
			Page<Registro> registros = registroService.findByColaborador_Matricula(colaboradorId, paginacao);
			return RegistroDto.converterRegistro(registros);
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(data, formatter);
			Page<Registro> registros = registroService.findByDataAndColaborador_Matricula(localDate, colaboradorId, paginacao);
			return RegistroDto.converterRegistro(registros);
		}
	}
	
	@GetMapping("/nao-normal")
	public List<Registro> listNaoNormal(){
		return registroService.findRegistroNaoNormal();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<RegistroDto> create(@RequestBody @Valid RegistroForm registroForm, UriComponentsBuilder uriBuilder){
		Registro registro = registroForm.converter(colaboradorService);
		registroService.save(registro);
		URI uri = uriBuilder.path("/registros/{id}").buildAndExpand(registro.getCodRegistro()).toUri();
		return ResponseEntity.created(uri).body(new RegistroDto(registro));
	}
	
	/*
	 * @GetMapping("/{id}") public ResponseEntity<RegistroDto>
	 * findById(@PathVariable Long id){ if(registroService.findById(id).isPresent())
	 * { return ResponseEntity.ok(new
	 * RegistroDto(registroService.findById(id).get())); } return
	 * ResponseEntity.notFound().build(); }
	 */
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<RegistroDto> update(@PathVariable Long id, @RequestBody @Valid RegistroUpdateForm registroForm){
		if(registroService.findById(id).isPresent()) {
			Registro registro = registroForm.update(id, registroService, colaboradorService);
			return ResponseEntity.ok(new RegistroDto(registro));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<RegistroDto> delete(@PathVariable Long id){
		if(registroService.findById(id).isPresent()) {
			registroService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
