package com.springsecurity.demo.dao;

import com.springsecurity.demo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);

}
