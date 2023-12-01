package com.glca.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glca.demo.entity.Role;
import com.glca.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

}
