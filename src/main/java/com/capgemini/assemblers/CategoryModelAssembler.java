package com.capgemini.assemblers;


import org.hibernate.EntityMode;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.persistence.Entity;

import com.capgemini.controllers.CategoryController;
import com.capgemini.entities.Category;


@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>>{
    
    @Override
    public EntityModel<Category> toModel(Category category) {
        return EntityModel.of(category, 
        linkTo(methodOn(CategoryController.class).getCategory(category.getId())).withSelfRel(),
        linkTo(methodOn(CategoryController.class).getCategories()).withRel("categories"));
  }
}
