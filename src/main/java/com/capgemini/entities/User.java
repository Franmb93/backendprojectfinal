package com.capgemini.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull
	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 4, max = 16, message = "Username must have a length of 4-16 characters")
	private String username;

	@Size(min = 8, message = "Password must have more than 8 characters")
	@NotNull
	@NotEmpty(message = "Password cannot be empty")
	private String password;

	@NotNull
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 4, max = 16, message = "Name must have a length of 4-16 characters")
	private String first_name;
	
	@NotNull
	@NotEmpty(message = "Last name cannot be empty")
	@Size(min = 4, max = 32, message = "Last name must have a length of 4-32 characters")
	private String last_name;

	@NotNull
	@NotEmpty(message = "Email cannot be empty")
	@Size(min = 4, max = 64, message = "Email must have a length of 4-32 characters")
	private String email;
	
	@NotNull
	@NotEmpty(message = "Phone cannot be empty")
	@Size(min = 8, max = 10, message = "Incorrect number of digits for phone (8-10)")
	private String phone;
	private String image;

	private String billing_address;
	
	@NotNull
	@Size(max = 255, message = "max. address is 255 characters")
	private String shipping_address;
	
	@NotNull
	@Min(value = 0, message = "Value of the wallet cannot be less than 0")
	private String wallet;

	@Min(value = 0, message = "Valorations cannot be under 0")
	@Max(value = 5, message = "Valorations cannot be higher than 5")
	private long valoraton;
	
	private int n_valorations;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Deal> deals;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Product> products;

}
