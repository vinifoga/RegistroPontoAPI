package br.com.fogaca.RegistroPonto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fogaca.RegistroPonto.model.Cargo;
import br.com.fogaca.RegistroPonto.service.CargoService;
import br.com.fogaca.RegistroPonto.service.DepartamentoService;

public class CargoUpdateForm {

	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String cbo;
	@NotNull
	private Long departamentoId;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCbo() {
		return cbo;
	}
	public void setCbo(String cbo) {
		this.cbo = cbo;
	}
	public Long getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
	}
	
	public Cargo update(Long id, CargoService cargoService, DepartamentoService departamentoService) {
		Cargo cargo = cargoService.findById(id).get();
		cargo.setCbo(this.cbo);
		cargo.setDepartamento(departamentoService.findById(id).get());
		cargo.setDescricao(this.descricao);
		return cargo;
	}
}
