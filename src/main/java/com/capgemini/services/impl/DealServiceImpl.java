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
		// TODO Auto-generated method stub
		return dealDao.findAll();
	}

	@Override
	public Deal getById(String id) {
		// TODO Auto-generated method stub
		return dealDao.getById(Long.parseLong(id));
	}

	@Override
	public Deal update(Deal deal) {
		// TODO Auto-generated method stub
		return dealDao.save(deal);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dealDao.deleteById(Long.parseLong(id));
	}

	
	
}
