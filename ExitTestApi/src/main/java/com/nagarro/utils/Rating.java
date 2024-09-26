package com.nagarro.utils;

import java.util.List;

import com.nagarro.entity.Review;

public class Rating {

	public static Integer computeWeightedAverageRating(List<Review> reviews) {

		int[] ratings = { 1, 2, 3, 4, 5 };
		int[] weights = { 0, 0, 0, 0, 0 };
		double weightedSum = 0;
		int totalWeight = 0;

		for (Review review : reviews) {
			if (review.getRating() > 0 && review.getRating() <= 5) {
				int rating = review.getRating();
				weights[rating - 1] += rating;
			}
		}

		for (int i = 0; i < ratings.length; i++) {
			weightedSum += ratings[i] * weights[i];
			totalWeight += weights[i];
		}
		double weightedAverage = weightedSum / totalWeight;

		return (int) Math.floor(weightedAverage);
	}
}
