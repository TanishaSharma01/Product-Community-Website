package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entity.Product;
import com.nagarro.entity.Review;
import com.nagarro.exception.ApiRequestException;
import com.nagarro.model.ResponseJson;
import com.nagarro.service.ProductService;
import com.nagarro.service.ReviewService;
import com.nagarro.utils.Constants;

@RestController
@CrossOrigin(origins = Constants.CLIENT_URL, allowCredentials = Constants.TRUE)
@RequestMapping(Constants.API_V1)
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;

	@Autowired
	ProductService productService;
	
	//List of all reviews
	@GetMapping("/reviews")
	public ResponseEntity<Object> getAllReviews(){
		try {
		List<Review> reviews = this.reviewService.getAllReviews();
 		return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson("All reviews fetched",reviews));
		}
		catch(Exception e) {
			throw new ApiRequestException("Reviews could not be fetched.");
		}
	}
	
	//add review for a product code
	@PutMapping("/reviews/add/{code}")
	public ResponseEntity<Object> saveReview(@PathVariable(name = "code") String productCode,
			@RequestBody Review review) {
		try {
			
		//fetch product for that product code
		Product product = this.productService.getProductByCode(productCode);
		
		//if product is null
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson("Product doesn't exist"));
		}
		
		//if review title, description or rating is null then review would not be added
		if(review.getTitle()==null || review.getDescription()==null || review.getRating()==null || 
				review.getTitle()=="" || review.getDescription()=="" || review.getRating()<1 || review.getRating()>5) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson("Review could not be added due to invalid value of a paramter."));
		}
		
		//get list of all reviews for that product
		List<Review> currentReviews = product.getReviews();
		review.setApproved(false);
		review.setProduct(product);// link current review with product
		currentReviews.add(review);

		Product updatedProduct = this.productService.updateProduct(product);

		return ResponseEntity.ok().body(new ResponseJson(updatedProduct));
		}
		catch(Exception e) {
			throw new ApiRequestException("Review could not be added.");
		}
	}
	
	//get count of reviews
	@GetMapping("/reviews/count-reviews")
	public Long countReview() {
		try {
		Long countReview= this.reviewService.getCountReview();
		return countReview;			
		}
		catch(Exception e) {
			throw new ApiRequestException("Rewiew count could not be fetched.");
		}
	}

}

