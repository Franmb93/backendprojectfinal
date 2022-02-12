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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.assemblers.UserModelAssembler;
import com.capgemini.entities.Usuario;
import com.capgemini.security.PasswordEncrypter;
import com.capgemini.services.IUserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class UserController {

	private IUserService service;

	private final UserModelAssembler assembler;

	UserController(IUserService service, UserModelAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Usuario>> getUsers() {
		List<EntityModel<Usuario>> users = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<Usuario> getUser(@PathVariable("id") long id) {
		Usuario user = service.findById(id);

		return assembler.toModel(user);
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody Usuario user) throws IOException {

		// Path rutaCompleta = Paths.get("//home//curso//Users//Resources//" +
		// imagen.getOriginalFilename()); //TODO fixear rutacompleta para corresponderse
		// con el pc actual.
		// Files.write(rutaCompleta, imagen.getBytes());
		// user.setImage(imagen.getOriginalFilename());


		user.setPassword(PasswordEncrypter.encode(user.getPassword()));
		
		EntityModel<Usuario> entityModel = assembler.toModel(service.save(user));
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
	public ResponseEntity<?> updateUser(@RequestBody Usuario newUser, @PathVariable long id) {
		Usuario oldUser = service.findById(id);

		newUser.setId(oldUser.getId());
		EntityModel<Usuario> entityModel = assembler.toModel(service.save(newUser));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

}