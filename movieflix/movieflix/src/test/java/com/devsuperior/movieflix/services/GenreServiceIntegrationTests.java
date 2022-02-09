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

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class GenreServiceIntegrationTests {

	@Autowired
	private GenreService service;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalGenres;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalGenres = 5L; 
		
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
		Page<GenreDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(5, result.getSize());
		Assertions.assertEquals(countTotalGenres, result.getTotalElements());
	}
	
	@Test
	public void findaAllPagedShouldReturnEmptyPageWhenPageDoesNotExists() {
		
		PageRequest pageRequest = PageRequest.of(50, 4);
		Page<GenreDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void findaAllPagedShouldReturnSortedPageWhenSortById() {
		
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id"));
		Page<GenreDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Terror", result.getContent().get(0).getName());
		Assertions.assertEquals("Comedy", result.getContent().get(1).getName());
		Assertions.assertEquals("Adventure", result.getContent().get(2).getName());
	}
	
	@Test
	public void findByIdShouldReturnGenreDTOWhenIdExists() {
		
		GenreDTO result = service.findById(existingId);
		
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals("Terror", result.getName());
	}
	
	@Test
	public void findByIdShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
}
