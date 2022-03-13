package br.com.fogaca.RegistroPonto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Role;
import br.com.fogaca.RegistroPonto.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> list(){
		return roleRepository.findAll();
	}

	public List<Role> findById(String roleId) {
		List<Role> roles = new ArrayList<>();
		Optional<Role> role = roleRepository.findById(roleId);
		roles.add(role.get());
		return roles;
	}

	public void save(Role role) {
		roleRepository.save(role);
	}

}
