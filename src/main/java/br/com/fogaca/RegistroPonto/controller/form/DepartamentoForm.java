package br.com.fogaca.RegistroPonto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fogaca.RegistroPonto.model.Departamento;

public class DepartamentoForm {
	
	@NotNull @NotEmpty
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Departamento converter() {
		return new Departamento(descricao);
	}
}
