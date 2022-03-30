package com.springsecurity.demo.dao;

import com.springsecurity.demo.entity.User;

public interface Userdao {
	User findByUserName(String userName);

	void save(User user);
}
