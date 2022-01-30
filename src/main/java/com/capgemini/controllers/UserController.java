package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.User;
import com.capgemini.services.IUserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private IUserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers(){
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") long id){
		return service.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User saveUser(@RequestBody User user){
		return service.update(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") long id){
		service.delete(id);
	}
	
}