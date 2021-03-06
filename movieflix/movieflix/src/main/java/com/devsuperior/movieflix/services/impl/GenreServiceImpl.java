package com.devsuperior.movieflix.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.GenreService;
import com.devsuperior.movieflix.services.exceptions.DataBaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class GenreServiceImpl implements GenreService{

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional
	public GenreDTO insert(GenreDTO dto) {
		Genre entity = new Genre();
		copyDtoToEntity(dto, entity);
		entity = genreRepository.save(entity);
		return new GenreDTO(entity);
		
	}

	public void delete(Long id) {
		try {
			genreRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}
	
	@Transactional
	public GenreDTO update(Long id, GenreDTO dto) {
		try {
			Genre entity = genreRepository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = genreRepository.save(entity);
			return new GenreDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional(readOnly = true)
	public Page<GenreDTO> findAllPaged(Pageable pageable){
		Page<Genre> list = genreRepository.findAll(pageable);
		return list.map(x -> new GenreDTO(x));
	}

	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		Optional<Genre> obj = genreRepository.findById(id);
		Genre entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new GenreDTO(entity, entity.getMovies());
	}
	
	private void copyDtoToEntity(GenreDTO dto, Genre entity) {
		entity.setName(dto.getName());
		
		entity.getMovies().clear();
		
		for(MovieDTO movDto : dto.getMovies()) {
			Movie movie = movieRepository.getById(movDto.getId());
			entity.getMovies().add(movie);
		}
	}
}
