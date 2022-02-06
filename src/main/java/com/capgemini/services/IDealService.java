package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Deal;

public interface IDealService {

	public List<Deal> findAll();
	public Deal findById(long id);
	public Deal save(Deal deal);
	public void delete(long id);
	
}
