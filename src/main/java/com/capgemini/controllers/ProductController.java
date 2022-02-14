package com.capgemini.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.assemblers.ProductModelAssembler;
import com.capgemini.entities.Product;
import com.capgemini.entities.Usuario;
import com.capgemini.services.IProductService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
	public EntityModel<Product> getProduct(@PathVariable long id) {

		Product product = serviceProduct.findById(id);

		return assembler.toModel(product);
	}

	@PostMapping(consumes={"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> newProduct(@RequestBody Product product) throws IOException {
		
		// Path rutaCompleta = Paths.get("//home//curso//projectfinal//Resources//" + imagen.getOriginalFilename());
		// Files.write(rutaCompleta, imagen.getBytes());
		// product.setImage(imagen.getOriginalFilename());
		
		EntityModel<Product> entityModel = assembler.toModel(serviceProduct.save(product));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@PutMapping(value = "/{id}", consumes={"application/json"})
	public ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable long id) {
		Product oldProduct = serviceProduct.findById(id);
		EntityModel<Product> entityModel = null;
		if (oldProduct != null) {
			oldProduct.setCategory(newProduct.getCategory());
			oldProduct.setDescription(newProduct.getDescription());
			oldProduct.setName(newProduct.getName());
			oldProduct.setWeight(newProduct.getWeight());
			oldProduct.setPrice(newProduct.getPrice());

			entityModel = assembler.toModel(serviceProduct.save(oldProduct));
		} else {
			newProduct.setId(oldProduct.getId());
			entityModel = assembler.toModel(serviceProduct.save(newProduct));
		}

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entityModel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		serviceProduct.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/{id}")
	public List<Product> findByUserId(@PathVariable long id){
		return serviceProduct.findByUserId(id);
	}

	@GetMapping("/category/{id}")
	public List<Product> findByCategoryId(@PathVariable long id){
		return serviceProduct.findByCategoryId(id);
	}


}