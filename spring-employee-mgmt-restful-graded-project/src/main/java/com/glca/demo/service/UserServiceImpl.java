package com.glca.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.glca.demo.entity.User;
import com.glca.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
