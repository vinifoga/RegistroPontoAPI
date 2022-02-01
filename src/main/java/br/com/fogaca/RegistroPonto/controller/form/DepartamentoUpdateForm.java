package br.com.fogaca.RegistroPonto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fogaca.RegistroPonto.model.Departamento;
import br.com.fogaca.RegistroPonto.service.DepartamentoService;

public class DepartamentoUpdateForm {

	@NotNull @NotEmpty
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Departamento update(Long id, DepartamentoService departamentoService) {
		Departamento departamento = departamentoService.findById(id).get();
		departamento.setDescricao(this.descricao);
		return departamento;
	}
}
