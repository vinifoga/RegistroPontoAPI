package br.com.fogaca.RegistroPonto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Cargo;

public class CargoDto {
	
	private Long id;
	private String descricao;
	private String cbo;
	private String departamento;

	public CargoDto(Cargo cargo) {
		this.id = cargo.getId();
		this.descricao = cargo.getDescricao();
		this.cbo = cargo.getCbo();
		this.departamento = cargo.getDepartamento().getDescricao();
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getCbo() {
		return cbo;
	}
	public String getDepartamento() {
		return departamento;
	}
	
	public static List<CargoDto> converterCargo(List<Cargo> cargos){
		return cargos.stream().map(CargoDto::new).collect(Collectors.toList());
	}
}
