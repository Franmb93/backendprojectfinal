package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Usuario;

@Repository
public interface IUserDao extends JpaRepository<Usuario, Long> {
public Usuario findByUsername(String username);

    @Query(value = "SELECT u.* FROM usuario u, product p WHERE u.id = p.user_id AND p.id=?1",
    nativeQuery = true)
	public Usuario findByProductId(long idProduct);
}
