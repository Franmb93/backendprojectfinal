package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.entities.Deal;
import com.capgemini.repository.IDealDao;
import com.capgemini.services.IDealService;

@Service
public class DealServiceImpl implements IDealService {

	@Autowired
	private IDealDao dealDao;
	
	@Override
	public List<Deal> getAll() {
		return dealDao.findAll();
	}

	@Override
	public Deal getById(String id) {
		return dealDao.findById(Long.parseLong(id)).get();
	}

	@Override
	public Deal update(Deal deal) {
		return dealDao.save(deal);
	}

	@Override
	public void delete(String id) {
		dealDao.deleteById(Long.parseLong(id));
	}

	
	
}