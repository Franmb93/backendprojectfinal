package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.entities.Order;
import com.capgemini.repository.IOrderDao;
import com.capgemini.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	
	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderDao.findAll();
	}

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		return orderDao.getById(Long.parseLong(id));
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub
		return orderDao.save(order);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		orderDao.deleteById(Long.parseLong(id));
	}

	
	
}
