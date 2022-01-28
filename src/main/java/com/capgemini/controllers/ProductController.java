package com.capgemini.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Product;
import com.capgemini.exceptions.ProductNotFoundException;
import com.capgemini.services.IProductService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ProductController {

	@Autowired
	private IProductService serviceProduct;

	@GetMapping("/api/products")
	public CollectionModel<EntityModel<Product>> getProducts() {
		 List<EntityModel<Product>> products = serviceProduct.getAll().stream()
			      .map(product -> EntityModel.of(product,
			          linkTo(methodOn(ProductController.class).getProduct(Long.toString(product.getId()))).withSelfRel(),
			          linkTo(methodOn(ProductController.class).getProducts()).withRel("products")))
			      .collect(Collectors.toList());
		 
		 return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());
	}

	@GetMapping("/api/products/{id}")
	public EntityModel<Product> getProduct(@PathVariable("id") String id) {
		Product product = null;
		try {
			product = serviceProduct.getById(id);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}

		return EntityModel.of(product, 
				linkTo(methodOn(ProductController.class).getProduct(id)).withSelfRel(),
				linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
				
				
	}

	@PostMapping("/products")
	public Product newProduct(@RequestBody Product product) {
		return serviceProduct.update(product);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product newProduct, @PathVariable String id) {
		Product oldProduct = serviceProduct.getById(id);

		if (oldProduct != null) {
			oldProduct.setCategory(newProduct.getCategory());
			oldProduct.setDescription(newProduct.getDescription());
			oldProduct.setName(newProduct.getName());
			oldProduct.setWeight(newProduct.getWeight());
			oldProduct.setPrice(newProduct.getPrice());
			return serviceProduct.update(oldProduct);
		} else {
			newProduct.setId(oldProduct.getId());
			return serviceProduct.update(newProduct);
		}
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") String id) {
		serviceProduct.delete(id);
	}

}