package br.com.fogaca.RegistroPonto.controller;

import java.net.URI;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import br.com.fogaca.RegistroPonto.controller.dto.UsuarioDto;
import br.com.fogaca.RegistroPonto.controller.dto.UsuarioDtoSenha;
import br.com.fogaca.RegistroPonto.controller.form.UsuarioForm;
import br.com.fogaca.RegistroPonto.controller.form.UsuarioUpdateForm;
import br.com.fogaca.RegistroPonto.controller.form.UsuarioUpdateSenhaForm;
import br.com.fogaca.RegistroPonto.model.Usuario;
import br.com.fogaca.RegistroPonto.service.ColaboradorService;
import br.com.fogaca.RegistroPonto.service.RoleService;
import br.com.fogaca.RegistroPonto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	@Cacheable(value = "listaUsuario")
	public List<UsuarioDto> list(Long usuarioId){
		if(usuarioId == null) {
			return UsuarioDto.converterUsuario(usuarioService.list());
		} else {
			List<Usuario> usuarios = new ArrayList<>();
			usuarios.add(usuarioService.findById(usuarioId).get());
			return UsuarioDto.converterUsuario(usuarios);
		}		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaUsuario", allEntries = true)
	public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
		Usuario usuario = usuarioForm.converter(colaboradorService, roleService);
		usuarioService.save(usuario);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
		if(usuarioService.findById(id).isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuarioService.findById(id).get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioDto> findByEmail(@PathVariable String email){
		if(usuarioService.findByEmail(email).isPresent()){
			return ResponseEntity.ok(new UsuarioDto(usuarioService.findByEmail(email).get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaUsuario", allEntries = true)
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateForm usuarioForm){
		if(usuarioService.findById(id).isPresent()) {
			Usuario usuario = usuarioForm.update(id, usuarioService, colaboradorService, roleService);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/reset-senha-padrao/{email}")
	@Transactional
	public ResponseEntity<UsuarioDtoSenha> updateSenha(@PathVariable String email){
		if(usuarioService.findByEmail(email).isPresent()) {			
			Usuario usuario = usuarioService.findByEmail(email).get();
			UsuarioUpdateSenhaForm usuarioUpdateSenhaForm = new UsuarioUpdateSenhaForm();
			String pattern = "\\S+";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(usuario.getNome().toLowerCase());
			if(m.find()) {
				String senhaPadrao = Normalizer.normalize(m.group(0), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")+"@"+LocalDate.now().getYear();
				usuarioUpdateSenhaForm.update(usuario, senhaPadrao);
			}			
			return ResponseEntity.ok(new UsuarioDtoSenha(usuario));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaUsuario", allEntries = true)
	public ResponseEntity<UsuarioDto> delete(@PathVariable Long id){
		if(usuarioService.findById(id).isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	

}
