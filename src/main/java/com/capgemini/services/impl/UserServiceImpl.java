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
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User getById(String id) {
		// TODO Auto-generated method stub
		return userDao.getById(Long.parseLong(id));
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		userDao.deleteById(Long.parseLong(id));
	}

}
