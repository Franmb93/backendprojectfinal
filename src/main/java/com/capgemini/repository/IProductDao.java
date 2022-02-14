package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.capgemini.entities.Product;
import com.capgemini.entities.Usuario;

@Repository
public interface IProductDao extends JpaRepository<Product, Long> {
	@Query(value = "SELECT p.* FROM product p WHERE p.user_id = ?1",
    nativeQuery = true)
    public List<Product> findByUserId(long idUser);

    @Query(value = "SELECT p.* FROM product p WHERE p.category_id = ?1",
    nativeQuery = true)
	public List<Product> findByCategoryId(long idCategory);


}
