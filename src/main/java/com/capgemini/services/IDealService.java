package com.capgemini.services;

import java.util.List;

import com.capgemini.entities.Deal;

public interface IDealService {

	public List<Deal> getAll();
	public Deal getById(String id);
	public Deal update(Deal deal);
	public void delete(String id);
	
}
