package com.capgemini.assemblers;

import com.capgemini.controllers.ProductController;
import com.capgemini.entities.Product;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product, //
        linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel(),
        linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
  }
}
    
