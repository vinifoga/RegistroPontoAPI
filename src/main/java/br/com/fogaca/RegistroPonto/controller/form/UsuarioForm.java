package br.com.fogaca.RegistroPonto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import br.com.fogaca.RegistroPonto.model.Usuario;
import br.com.fogaca.RegistroPonto.service.CargoService;

public class UsuarioForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	@NotNull
	private Long cargoId;
	
	@Type(type="true_false")
	private boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getCargoId() {
		return cargoId;
	}
	
	public void setCargoId(Long cargoId) {
		this.cargoId = cargoId;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Usuario converter(CargoService cargoService) {
		return new Usuario(nome, email, senha, cargoService.findById(cargoId).get(), ativo);
	}

}
