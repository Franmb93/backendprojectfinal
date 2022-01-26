package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.User;
import com.capgemini.repository.IUserDao;
import com.capgemini.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}

	@Override
	public User getById(String id) {
		return userDao.findById(Long.parseLong(id)).get();
	}

	@Override
	public User update(User user) {
		return userDao.save(user);
	}

	@Override
	public void delete(String id) {
		userDao.deleteById(Long.parseLong(id));
	}

}
