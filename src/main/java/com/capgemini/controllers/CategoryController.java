package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Category;
import com.capgemini.services.ICategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

	@Autowired
	private ICategoryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> getCategories(){
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable("id") long id){
		return service.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Category saveCategory(@RequestBody Category category){
		return service.update(category);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable("id") long id){
		service.delete(id);
	}
	
}