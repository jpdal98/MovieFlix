package com.devsuperior.movieflix.factories;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

public class ReviewFactory {

	public static Review createReview() {
		Review review = new Review(1L, "PQP! kkkkkkkkkk");
		review.setUser(new User(1L, "Ana", "Ana37@hotmail.com", "ana123"));
		review.setMovie(new Movie(1L, "Esqueceram de mim", 1990, "https://br.web.img3.acsta.net/medias/nmedia/18/93/93/95/20287484.jpg", "Uma família de Chicago planeja passar o Natal em Paris. Porém, em meio às confusões da viagem, um dos filhos, Kevin (Macaulay Culkin), acaba esquecido em casa. O garoto de apenas oito anos é obrigado a se virar sozinho e defender a casa de dois insistentes ladrões."));
		return review;
	}
	
	public static ReviewDTO createReviewDTO() {
		ReviewDTO reviewDTO = new ReviewDTO(createReview());
		return reviewDTO;
	}
}
