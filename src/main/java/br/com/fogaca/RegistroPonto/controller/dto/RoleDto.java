package br.com.fogaca.RegistroPonto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fogaca.RegistroPonto.model.Role;

public class RoleDto {

	private String nomeRole;

	public RoleDto(Role role) {
		this.nomeRole = role.getNomeRole();
	}

	public String getNomeRole() {
		return nomeRole;
	}
	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public static List<RoleDto> converterCargo(List<Role> roles) {
		return roles.stream().map(RoleDto::new).collect(Collectors.toList());
	}
}
