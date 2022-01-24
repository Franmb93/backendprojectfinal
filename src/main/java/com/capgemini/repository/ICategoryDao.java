package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Long> {

}
