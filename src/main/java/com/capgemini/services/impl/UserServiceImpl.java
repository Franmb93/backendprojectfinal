package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.User;
import com.capgemini.exceptions.UserNotFoundException;
import com.capgemini.repository.IUserDao;
import com.capgemini.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(long id) {
		return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@Override
	public User update(User user) {
		return userDao.save(user);
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

}
