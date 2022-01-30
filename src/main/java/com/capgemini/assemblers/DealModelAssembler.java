package com.capgemini.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.capgemini.controllers.DealController;
import com.capgemini.entities.Deal;

@Component
public class DealModelAssembler implements RepresentationModelAssembler<Deal, EntityModel<Deal>>{

    @Override
    public EntityModel<Deal> toModel(Deal deal) {
        return EntityModel.of(deal, //
        linkTo(methodOn(DealController.class).getDeal(deal.getId())).withSelfRel(),
        linkTo(methodOn(DealController.class).getDeals()).withRel("deals"));
    }
    
}
