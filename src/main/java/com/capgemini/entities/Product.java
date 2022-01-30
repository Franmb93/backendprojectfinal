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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty(message = "name cannot be empty")
	@Size(min = 4, max = 16, message = "name must have a length of 4-16 characters")
	private String name;
	
	@NotEmpty(message = "description field cannot be empty")
	@Size(max = 255, message = "max. message is 255 characters")
	private String description;
	
	@Min(value = 1, message = "Price must be higher than 1")
	private double price;
	
	@Min(value = 0, message = "weight must be higher than 0")
	private double weight;

	private String image;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDateTime published_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@NotNull
	@JsonBackReference
	private Category category;
	
	@OneToOne(optional = true, mappedBy="product")
	@JsonManagedReference
	private Deal deal;
	
}
