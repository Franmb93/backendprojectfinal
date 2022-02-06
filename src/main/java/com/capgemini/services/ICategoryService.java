package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Category;

public interface ICategoryService {

	public List<Category> findAll();
	public Category findById(long id);
	public Category save(Category category);
	public void delete(long id);
	
}
