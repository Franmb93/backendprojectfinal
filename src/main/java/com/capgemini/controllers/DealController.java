package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Deal;
import com.capgemini.services.IDealService;

@RestController
@RequestMapping(value = "/api/deals")
public class DealController {

	@Autowired
	private IDealService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Deal> getDeals(){
		return service.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Deal getDeal(@PathVariable("id") String id){
		return service.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Deal saveDeal(@RequestBody Deal deal){
		return service.update(deal);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteDeal(@PathVariable("id") String id){
		service.delete(id);
	}
	
}