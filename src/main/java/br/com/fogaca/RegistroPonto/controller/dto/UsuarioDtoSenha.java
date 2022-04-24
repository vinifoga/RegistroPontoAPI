package br.com.fogaca.RegistroPonto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Usuario;

public class UsuarioDtoSenha {

	private Long id;
	private String senha;
	
	public UsuarioDtoSenha (Usuario usuario) {
		this.id = usuario.getId();
		this.senha = usuario.getSenha();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public static List<UsuarioDtoSenha> converterUsuario(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDtoSenha::new).collect(Collectors.toList());
	}
	
	
}
