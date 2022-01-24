package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Product;
import com.capgemini.services.IProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

	@Autowired
	private IProductService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getProducts(){
		return service.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") String id){
		return service.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product){
		return service.update(product);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") String id){
		service.delete(id);
	}
	
}