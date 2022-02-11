package com.capgemini.assemblers;

import com.capgemini.controllers.UserController;
import com.capgemini.entities.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

    @Override
    public EntityModel<Usuario> toModel(Usuario user) {
        return EntityModel.of(user, //
        linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
        linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
    }
    
}
