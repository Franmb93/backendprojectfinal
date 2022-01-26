package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Product;
import com.capgemini.repository.IProductDao;
import com.capgemini.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	
	@Override
	public List<Product> getAll() {
		return productDao.findAll();
	}

	@Override
	public Product getById(String id) {
		return productDao.findById(Long.parseLong(id)).get();
	}

	@Override
	public Product update(Product product) {
		return productDao.save(product);
	}

	@Override
	public void delete(String id) {
		productDao.deleteById(Long.parseLong(id));
	}

}
