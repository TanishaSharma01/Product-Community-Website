package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.ReviewDao;
import com.nagarro.entity.Review;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDao reviewDao;
	
	//Get List of all reviews
	public List<Review> getAllReviews(){
		return this.reviewDao.findAll();
	}
	
	//find review by id
	public Review getReviewById(int id){
		return this.reviewDao.findById(id);
	}

	//count of all reviews
	public Long getCountReview()
	{
		return this.reviewDao.count();
	}
}