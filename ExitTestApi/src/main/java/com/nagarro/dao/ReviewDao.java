package com.nagarro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Long>{
	Review findById(int id);
	
   	@Query("SELECT p FROM Review p WHERE p.approved =TRUE ")
   	List<Review> getApprovedReviews();
   	
   	@Query("SELECT p FROM Review p WHERE p.approved = FALSE")
   	List<Review> getDisapprovedReviews();
}

