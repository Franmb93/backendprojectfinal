package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Product;

public interface IProductService {

	public List<Product> findAll();
	public Product findById(long id);
	public Product save(Product product);
	public void delete(long id);
	public List<Product> findByUserId(long id);
	public List<Product> findByCategoryId(long id);
	
}
