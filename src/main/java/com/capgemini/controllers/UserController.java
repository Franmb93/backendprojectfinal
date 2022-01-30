package com.capgemini.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.assemblers.UserModelAssembler;
import com.capgemini.entities.User;
import com.capgemini.services.IUserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private IUserService service;

	private final UserModelAssembler assembler;

	UserController(IUserService service, UserModelAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<User> getUser(@PathVariable("id") long id) {
		User user = service.findById(id);

		return assembler.toModel(user);
	}

	@PostMapping
	public User saveUser(@RequestBody User user) {
		return service.update(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User newUser, @PathVariable long id){
		//TODO fixupdates
		User oldUser = service.findById(id);

		if(oldUser != null){
			newUser.setId(id);
		}

		return newUser;
	}

}