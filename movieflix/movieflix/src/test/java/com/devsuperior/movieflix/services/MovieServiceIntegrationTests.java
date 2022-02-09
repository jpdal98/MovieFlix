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

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class MovieServiceIntegrationTests {

	@Autowired
	private MovieService service;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalMovies;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalMovies = 30L; 
		
	}
	
	@Test
	public void deleteShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
	@Test
	public void findaAllPagedShouldReturnPageWhenPage0Size5() {
		
		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<MovieDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(10, result.getSize());
		Assertions.assertEquals(countTotalMovies, result.getTotalElements());
	}
	
	@Test
	public void findaAllPagedShouldReturnEmptyPageWhenPageDoesNotExists() {
		
		PageRequest pageRequest = PageRequest.of(50, 4);
		Page<MovieDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void findaAllPagedShouldReturnSortedPageWhenSortById() {
		
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id"));
		Page<MovieDTO> result = service.findAllPaged(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Constatine", result.getContent().get(0).getTitle());
		Assertions.assertEquals("Invocação do Mal", result.getContent().get(1).getTitle());
		Assertions.assertEquals("O chamado", result.getContent().get(2).getTitle());
	}
	
	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
		
		MovieDTO result = service.findById(existingId);
		
		Assertions.assertEquals(1L, result.getId());
		Assertions.assertEquals("Constatine", result.getTitle());
	}
	
	@Test
	public void findByIdShouldReturnThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
}
