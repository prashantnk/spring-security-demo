package com.springsecurity.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.demo.dao.RoleDao;
import com.springsecurity.demo.dao.Userdao;
import com.springsecurity.demo.entity.Role;
import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.model.MUser;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private Userdao userdao;
	@Autowired
	private RoleDao roledao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findUserbyName(String name) {
		return userdao.findByUserName(name);
	}

	@Override
	@Transactional
	public void save(MUser mu) {
		User user = new User();
		user.setUsername(mu.getUsername());
		user.setPassword(passwordEncoder.encode(mu.getPassword()));
		user.setFirstName(mu.getFirstName());
		user.setLastName(mu.getLastName());
		user.setEmail(mu.getEmail());
		var roles = new ArrayList<Role>();

		System.out.println("calling roledao!!");
		roles.add(roledao.findRoleByName("ROLE_EMPLOYEE"));

		user.setRoles(roles);
		userdao.save(user);
	}

//	copy pasted i don't know about it
//	important because it is being used by spring security to load user

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userdao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
