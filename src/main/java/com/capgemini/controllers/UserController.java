package com.capgemini.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.assemblers.UserModelAssembler;
import com.capgemini.entities.User;
import com.capgemini.services.IUserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
	public ResponseEntity<?> saveUser(@RequestBody User user, @RequestParam(name = "file") MultipartFile imagen) throws IOException {

		Path rutaCompleta = Paths.get("//home//curso//Users//Resources//" + imagen.getOriginalFilename()); //TODO fixear rutacompleta para corresponderse con el pc actual.
		Files.write(rutaCompleta, imagen.getBytes());
		user.setImage(imagen.getOriginalFilename());

		EntityModel<User> entityModel = assembler.toModel(service.save(user));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable long id){
		User oldUser = service.findById(id);

		newUser.setId(oldUser.getId());
		EntityModel<User> entityModel = assembler.toModel(service.save(newUser));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

}