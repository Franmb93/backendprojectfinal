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
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public Product getById(String id) {
		// TODO Auto-generated method stub
		return productDao.getById(Long.parseLong(id));
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		productDao.deleteById(Long.parseLong(id));
	}

}
