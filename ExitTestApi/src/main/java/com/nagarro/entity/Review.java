package com.nagarro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //review id
	@Column(name="id")
	private int id;
	
	@Column(name = "title") //review title
	private String title;
	
	@Column(name = "description") //review description
	private String description;	
 
	@Column(name="rating") //review rating
	private Integer rating;
	
	@Column(name="approved")
	private Boolean approved; 
	
	@Column(name="createdBy") //review created by
	private String createdBy;
	 
	//Many to One mapping, a review can be linked to only one product
	@ManyToOne()
	@JoinColumn()
	@JsonIgnore
	private Product product;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Review(int id, String title, String description, Integer rating, Boolean approved, String createdBy,
			Product product) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.approved = approved;
		this.createdBy = createdBy;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Override
	public String toString() {
		return "Review [id=" + id + ", title=" + title + ", description=" + description + " , rating=" + rating + ", approved=" + approved + ", product=" + product
				+ "]";
	}
}

