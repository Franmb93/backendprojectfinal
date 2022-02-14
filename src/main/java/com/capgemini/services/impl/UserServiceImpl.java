package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Usuario;
import com.capgemini.exceptions.UserNotFoundException;
import com.capgemini.repository.IUserDao;
import com.capgemini.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public List<Usuario> findAll() {
		return userDao.findAll();
	}

	@Override
	public Usuario findById(long id) {
		return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@Override
	public Usuario save(Usuario user) {
		return userDao.save(user);
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public Usuario findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
