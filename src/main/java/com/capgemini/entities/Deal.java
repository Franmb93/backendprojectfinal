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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.capgemini.entities.validations.dates.ValidDates;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ValidDates //TODO ¡no se ha comprobado si funciona!
public class Deal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 1, message = "Price must be higher than 1")
	private double price;
	
	@NotNull
	@Size(max = 255, message = "max. address is 255 characters")
	private String shipping_address;
	
	@Min(value = 0, message = "Valorations cannot be under 0")
	@Max(value = 5, message = "Valorations cannot be higher than 5")
	private long valoration;
	private int n_valorations;
	

	private LocalDateTime ordered_date;
	
	@OneToOne
	@JsonBackReference
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;
	
}
