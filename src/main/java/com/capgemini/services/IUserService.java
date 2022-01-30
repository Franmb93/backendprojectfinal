package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.User;

public interface IUserService {

	public List<User> findAll();
	public User findById(long id);
	public User update(User user);
	public void delete(long id);
	
}
