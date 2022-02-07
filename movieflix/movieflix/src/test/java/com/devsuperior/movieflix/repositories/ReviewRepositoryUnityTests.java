package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.factories.ReviewFactory;

@DataJpaTest
public class ReviewRepositoryUnityTests {

	@Autowired 
	private ReviewRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalReview;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 1l;
		nonExistingId = 1000L;
		countTotalReview = 5l;
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() throws Exception {

		Review review = ReviewFactory.createReview();
		review.setId(null);
		
		review = repository.save(review);
		
		Assertions.assertNotNull(review.getId());
		Assertions.assertEquals(countTotalReview + 1, review.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() throws Exception {
		
		repository.deleteById(existingId);
		Optional<Review> result = repository.findById(existingId);
		
		Assertions.assertFalse(result.isPresent());
	}
	

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() throws Exception {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
					repository.deleteById(nonExistingId);
				});
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
		
		Optional<Review> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		
		Optional<Review> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
