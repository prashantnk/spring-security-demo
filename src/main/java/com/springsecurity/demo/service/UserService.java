package com.springsecurity.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.model.MUser;

public interface UserService extends UserDetailsService {
	public User findUserbyName(String name);

	public void save(MUser mu);
}
