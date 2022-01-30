package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Product;

public interface IProductService {

	public List<Product> findAll();
	public Product findById(long id);
	public Product update(Product product);
	public void delete(long id);
	
}
