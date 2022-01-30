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
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findById(long id) {
		return productDao.findById(id).get();
	}

	@Override
	public Product update(Product product) {
		return productDao.save(product);
	}

	@Override
	public void delete(long id) {
		productDao.deleteById(id);
	}

}
