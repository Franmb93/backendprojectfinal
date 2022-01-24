package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Category;

public interface ICategoryService {

	public List<Category> getAll();
	public Category getById(String id);
	public Category update(Category category);
	public void delete(String id);
	
}
