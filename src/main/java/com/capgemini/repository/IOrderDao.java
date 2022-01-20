package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Order;

@Repository
public interface IOrderDao extends JpaRepository<Order, Long> {

}
