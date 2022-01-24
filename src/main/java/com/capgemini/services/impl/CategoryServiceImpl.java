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
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public Category getById(String id) {
		// TODO Auto-generated method stub
		return categoryDao.getById(Long.parseLong(id));
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.save(category);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		categoryDao.deleteById(Long.parseLong(id));
	}

}
