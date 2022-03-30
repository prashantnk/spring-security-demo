package com.springsecurity.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springsecurity.demo.entity.User;

@Repository
public class UserdaoImpl implements Userdao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {
		Session currSession = sessionFactory.getCurrentSession();
		Query<User> q = currSession.createQuery("from User where username=:theUser", User.class);
		q.setParameter("theUser", userName);
		User u = q.getSingleResult();
		return u;
	}

	@Override
	public void save(User user) {
		Session currSession = sessionFactory.getCurrentSession();
		currSession.saveOrUpdate(user);
	}

}
