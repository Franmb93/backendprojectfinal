package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.repository.ICategoryDao;
import com.capgemini.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public List<Category> getAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category getById(String id) {
		return categoryDao.findById(Long.parseLong(id)).get();
	}

	@Override
	public Category update(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public void delete(String id) {
		categoryDao.deleteById(Long.parseLong(id));
	}

}
