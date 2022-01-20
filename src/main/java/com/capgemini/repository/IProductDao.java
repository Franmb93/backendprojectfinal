package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Product;

@Repository
public interface IProductDao extends JpaRepository<Product, Long> {

}
