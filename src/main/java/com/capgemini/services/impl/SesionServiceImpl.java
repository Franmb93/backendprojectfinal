package com.capgemini.services.impl;

import java.util.List;

import com.capgemini.entities.Sesion;
import com.capgemini.repository.ISesionDao;
import com.capgemini.services.ISesionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionServiceImpl implements ISesionService{

    @Autowired
    private ISesionDao sesionDao;

	@Override
	public List<Sesion> findAll() {
		return sesionDao.findAll();
	}

	@Override
	public Sesion findById(long id) {
		return sesionDao.findById(id).get();
	}

	@Override
	public Sesion save(Sesion sesion) {
		return sesionDao.save(sesion);
	}

	@Override
	public void delete(long id) {
		sesionDao.deleteById(id);
	}

    public Sesion findByUsername(String username){
        return sesionDao.findByUsername(username);
    }
    
}