package br.com.fogaca.RegistroPonto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Departamento;

public class DepartamentoDto {

	private Long id;
	private String descricao;
	
	
	public DepartamentoDto(Departamento departamento) {
		this.id = departamento.getId();
		this.descricao = departamento.getDescricao();
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public static List<DepartamentoDto> converterDepartamento(List<Departamento> departamentos){
		return departamentos.stream().map(DepartamentoDto::new).collect(Collectors.toList());
	}
}
