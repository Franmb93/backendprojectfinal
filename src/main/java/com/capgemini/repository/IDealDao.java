package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Deal;

@Repository
public interface IDealDao extends JpaRepository<Deal, Long> {

}
