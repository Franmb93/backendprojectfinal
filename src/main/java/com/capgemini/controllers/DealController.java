package com.capgemini.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.assemblers.DealModelAssembler;
import com.capgemini.entities.Deal;
import com.capgemini.services.IDealService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/deals")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DealController {

	private IDealService service;

	private final DealModelAssembler assembler;

	DealController(IDealService service, DealModelAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Deal>> getDeals() {
		List<EntityModel<Deal>> deals = service.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(deals, linkTo(methodOn(DealController.class).getDeals()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<Deal> getDeal(@PathVariable long id) {
		Deal deal = service.findById(id);

		return assembler.toModel(deal);
	}

	@PostMapping
	public Deal saveDeal(@RequestBody Deal deal) {
		return service.save(deal);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDeal(@PathVariable("id") long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateDeal(@RequestBody Deal newDeal, @PathVariable long id){
		//TODO fix updates
		Deal oldDeal = service.findById(id);
		newDeal.setId(oldDeal.getId());
		EntityModel<Deal> entityModel = assembler.toModel(newDeal);

		return ResponseEntity
		.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		.body(entityModel);
	}

}