package com.capgemini.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	
	private String first_name;
	private String last_word;
	
	private String email;
	private String phone;
	private String image;
	
	private String billing_address;
	private String shipping_address;
	private String wallet;
	
	private long valoraton;
	private int n_valorations;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "user")
	private List<Product> products;
	
}
