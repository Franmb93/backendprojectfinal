package com.capgemini.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Order;
import com.capgemini.services.IOrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

	private IOrderService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Order> getOrders(){
		return service.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable("id") String id){
		return service.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Order saveOrder(@RequestBody Order order){
		return service.update(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteOrder(@PathVariable("id") String id){
		service.delete(id);
	}
	
}