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

import com.capgemini.assemblers.CategoryModelAssembler;
import com.capgemini.entities.Category;
import com.capgemini.services.ICategoryService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/categories")
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
	public Category updateCategory(@RequestBody Category newCategory) {
		//TODO fix updates
		Category oldCategory = service.findById(newCategory.getId());

		if(oldCategory != null){
			newCategory.setId(oldCategory.getId());
		}

		return service.update(newCategory);
	}

	@DeleteMapping
	public void deleteCategory(@PathVariable("id") long id) {
		service.delete(id);
	}

	@PostMapping("/{id}")
	public Category saveCategory(@RequestBody Category category){
		return service.update(category);
	}

}