package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Product;

public interface IProductService {

	public List<Product> getAll();
	public Product getById(String id);
	public Product update(Product product);
	public void delete(String id);
	
}
