package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Order;

public interface IOrderService {

	public List<Order> getAll();
	public Order getById(String id);
	public Order update(Order order);
	public void delete(String id);
	
}
