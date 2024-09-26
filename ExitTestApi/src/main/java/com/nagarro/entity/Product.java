package com.nagarro.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Product {
	@Id
	@Column(name="code", unique=true) //product code to be unique
	private String code;
	
	@Column(name="name") //product name
	private String name;
	
	@Column(name="brand") //product brand
	private String brand;
	
	@Transient //product rating to be set transient
	private Integer rating;
	
	//mapped by one to many i.e. a product can have multiple reviews
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	public Product(String code, String name, String brand,List<Review> reviews) {
		super();
		this.code = code;
		this.name = name;
		this.brand = brand;
		this.reviews = reviews;
	}

	public Product() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void setId(String id) {
		this.setCode(id);
	}
	
	public String getId() {
		return this.getCode();
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
