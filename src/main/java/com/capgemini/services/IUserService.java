package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.User;

public interface IUserService {

	public List<User> getAll();
	public User getById(String id);
	public User update(User user);
	public void delete(String id);
	
}
