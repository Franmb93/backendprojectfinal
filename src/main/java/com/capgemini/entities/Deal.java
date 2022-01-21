package com.capgemini.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Deal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double price;
	
	private String shipping_address;
	
	private long valoration;
	private int n_valorations;
	
	private LocalDateTime ordered_date;
	
	@OneToOne
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
}
