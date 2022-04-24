package br.com.fogaca.RegistroPonto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.fogaca.RegistroPonto.model.Usuario;
import br.com.fogaca.RegistroPonto.service.UsuarioService;

public class UsuarioUpdateSenhaForm {

	@NotNull @NotEmpty
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario update(Usuario usuario, String senhaPadrao) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(senhaPadrao));
		return usuario;
	}
}
