package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.factories.ReviewFactory;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.DataBaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.impl.ReviewServiceImpl;

@ExtendWith(SpringExtension.class)
public class ReviewServiceUnityTests {

	@InjectMocks
	private ReviewServiceImpl service;
	
	@Mock
	private ReviewRepository reviewRepository;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private Review review;
	private ReviewDTO reviewDto;
	private PageImpl<Review> page;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 2L;
		nonExistingId = 1000L;
		dependentId = 4L;
		review = ReviewFactory.createReview();
		reviewDto = ReviewFactory.createReviewDTO();
		page = new PageImpl<>(List.of(review));
		
		Mockito.when(reviewRepository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Mockito.when(reviewRepository.save(ArgumentMatchers.any())).thenReturn(review);
		
		Mockito.when(reviewRepository.findById(existingId)).thenReturn(Optional.of(review));
		Mockito.when(reviewRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(reviewRepository.getById(existingId)).thenReturn(review);
		Mockito.when(reviewRepository.getById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.doNothing().when(reviewRepository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(reviewRepository)
			   .deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(reviewRepository)
		   .deleteById(dependentId);
		
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldThrowDataBaseExceptionWhenDependentId() {
		
		Assertions.assertThrows(DataBaseException.class, () -> {
			service.delete(dependentId);
		});
		Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(dependentId);
	}
	
	@Test
	public void updateShouldReturnReviewDTOWhenIdExists() {

		ReviewDTO result = service.update(existingId, reviewDto);
		
		Assertions.assertNotNull(result);
	}
	
	@Test 
	public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, reviewDto);
		});
	}
	
	@Test
	public void findAllPagedShouldReturnPage() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<ReviewDTO> result = service.findAllPaged(pageable);
		
		Assertions.assertNotNull(result);
		Mockito.verify(reviewRepository, Mockito.times(1)).findAll(pageable);
	}
	
	@Test 
	public void findByIdShouldReturnPageWhenIdExists() {
		
		Optional<Review> result = reviewRepository.findById(existingId);
		
		Assertions.assertNotNull(result);
		Mockito.verify(reviewRepository, Mockito.times(1)).findById(existingId);
	} 
	
	@Test 
	public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
		Mockito.verify(reviewRepository, Mockito.times(1)).findById(nonExistingId);
	} 
}
