package com.capgemini.repository;

import org.springframework.stereotype.Repository;

import com.capgemini.entities.Sesion;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ISesionDao extends JpaRepository<Sesion, Long> {
    public Sesion findByUsername(String username);
}