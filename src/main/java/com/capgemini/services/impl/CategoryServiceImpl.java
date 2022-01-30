package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.CategoryNotFoundException;
import com.capgemini.repository.ICategoryDao;
import com.capgemini.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(long id) {
		return categoryDao.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
	}

	@Override
	public Category update(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public void delete(long id) {
		categoryDao.deleteById(id);
	}

}
