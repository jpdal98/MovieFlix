package com.devsuperior.movieflix.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class ReviewServiceIntegrationTests {

	@Autowired
	private ReviewService service;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalReviews;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalReviews = 5L; 
		
	}
	
	@Test
	public void deleteShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findaAllPagedShouldReturnPageWhenPage0Size5() {
		
		PageRequest pageRequest = PageRequest.of(0, 5);
		Page<ReviewDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(5, result.getSize());
		Assertions.assertEquals(countTotalReviews, result.getTotalElements());
	}
	
	@Test
	public void findaAllPagedShouldReturnEmptyPageWhenPageDoesNotExists() {
		
		PageRequest pageRequest = PageRequest.of(50, 4);
		Page<ReviewDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void findaAllPagedShouldReturnSortedPageWhenSortById() {
		
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id"));
		Page<ReviewDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Filme muito bom!!", result.getContent().get(0).getText());
		Assertions.assertEquals("Achei fraco e previsivel", result.getContent().get(1).getText());
		Assertions.assertEquals("kkkkkkkkk", result.getContent().get(2).getText());
	}
	
	@Test
	public void findByIdShouldReturnReviewDTOWhenIdExists() {
		
		ReviewDTO result = service.findById(existingId);
		
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals("Filme muito bom!!", result.getText());
	}
	
	@Test
	public void findByIdShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
}
