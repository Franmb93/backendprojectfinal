package com.capgemini.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.capgemini.assemblers.ProductModelAssembler;
import com.capgemini.entities.Product;
import com.capgemini.services.IProductService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private IProductService serviceProduct;

	private final ProductModelAssembler assembler;

	ProductController(IProductService serviceProduct, ProductModelAssembler assembler) {
		this.serviceProduct = serviceProduct;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Product>> getProducts() {
		List<EntityModel<Product>> products = serviceProduct.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<Product> getProduct(@PathVariable("id") long id) {

		Product product = serviceProduct.findById(id);

		return assembler.toModel(product);
	}

	@PostMapping
	public Product newProduct(@RequestBody Product product) {
		return serviceProduct.update(product);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product newProduct, @PathVariable long id) {
		Product oldProduct = serviceProduct.findById(id);

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

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") long id) {
		serviceProduct.delete(id);
	}

}