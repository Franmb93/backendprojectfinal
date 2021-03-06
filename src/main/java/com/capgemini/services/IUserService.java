package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Usuario;




public interface IUserService {


	public List<Usuario> findAll();
	public Usuario findById(long id);
	public Usuario save(Usuario user);
	public void delete(long id);
	public Usuario findByUsername(String username);
}
