package com.capgemini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.entities.Deal;
import com.capgemini.exceptions.DealNotFoundException;
import com.capgemini.repository.IDealDao;
import com.capgemini.services.IDealService;

@Service
public class DealServiceImpl implements IDealService {

	@Autowired
	private IDealDao dealDao;
	
	@Override
	public List<Deal> findAll() {
		return dealDao.findAll();
	}

	@Override
	public Deal findById(long id) {
		return dealDao.findById(id).orElseThrow(() -> new DealNotFoundException(id));
	}

	@Override
	public Deal save(Deal deal) {
		return dealDao.save(deal);
	}

	@Override
	public void delete(long id) {
		dealDao.deleteById(id);
	}

	
	
}
