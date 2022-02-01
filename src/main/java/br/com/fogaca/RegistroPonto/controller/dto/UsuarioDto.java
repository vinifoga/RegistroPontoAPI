package br.com.fogaca.RegistroPonto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;
	private boolean ativo;
	private String cargo;
	
	public UsuarioDto (Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.ativo = usuario.isAtivo();
		this.cargo = usuario.getCargo().getDescricao();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public boolean isAtivo() {
		return ativo;
	}
	
	public String getCargo() {
		return cargo;
	}

	public static List<UsuarioDto> converterUsuario(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	
}
