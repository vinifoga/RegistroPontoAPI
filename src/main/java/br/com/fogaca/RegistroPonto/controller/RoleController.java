package br.com.fogaca.RegistroPonto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fogaca.RegistroPonto.model.Role;

import br.com.fogaca.RegistroPonto.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	@Cacheable(value = "listaRoles")
	public List<Role> list(String id){
		if(id == null) {
			return roleService.list();
		} else {
			List<Role> roles = new ArrayList<>();
			roles.addAll(roleService.findById(id));
			return roles;
		}		
	}
	
}
