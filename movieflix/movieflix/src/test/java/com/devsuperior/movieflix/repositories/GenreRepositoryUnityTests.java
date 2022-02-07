package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.factories.GenreFactory;

@DataJpaTest
public class GenreRepositoryUnityTests {

	@Autowired
	private GenreRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalGenre;
	
	@BeforeEach
	public void setup() throws Exception {
		
		existingId = 1l;
		nonExistingId = 1000L;
		countTotalGenre = 5l;
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() throws Exception {

		Genre genre = GenreFactory.createGenre();
		genre.setId(null);
		
		genre = repository.save(genre);
		
		Assertions.assertNotNull(genre.getId());
		Assertions.assertEquals(countTotalGenre + 1, genre.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() throws Exception {
		
		repository.deleteById(existingId);
		Optional<Genre> result = repository.findById(existingId);
		
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
		
		Optional<Genre> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
		
		Optional<Genre> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
