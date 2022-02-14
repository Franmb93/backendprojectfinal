package com.capgemini.repository;

import com.capgemini.entities.FileDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileDBDao extends JpaRepository<FileDB, String> {
    
}
