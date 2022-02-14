package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.capgemini.entities.Usuario;

@Repository
public interface IUserDao extends JpaRepository<Usuario, Long> {
public Usuario findByUsername(String username);
@Query(value = "SELECT u.* FROM product p, usuario u WHERE p.id = ?1 AND u.id = p.user_id group by u.id;",
nativeQuery = true)
public Usuario findUserByProductId(long idUser);
}
