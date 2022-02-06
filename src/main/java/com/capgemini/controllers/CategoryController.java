package com.capgemini.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.apache.catalina.connector.Response;
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

import com.capgemini.assemblers.CategoryModelAssembler;
import com.capgemini.entities.Category;
import com.capgemini.services.ICategoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryController {

	private ICategoryService service;

	private final CategoryModelAssembler assembler;

	CategoryController(ICategoryService service, CategoryModelAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Category>> getCategories() {
		List<EntityModel<Category>> categories = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<Category> getCategory(@PathVariable("id") long id) {
		return assembler.toModel(service.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody Category newCategory) {

		Category oldCategory = service.findById(newCategory.getId());
		newCategory.setId(oldCategory.getId());
		EntityModel<Category> entityModel = assembler.toModel(service.save(newCategory));

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> saveCategory(@RequestBody Category category){
		EntityModel<Category> entityModel = assembler.toModel(service.save(category));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

}