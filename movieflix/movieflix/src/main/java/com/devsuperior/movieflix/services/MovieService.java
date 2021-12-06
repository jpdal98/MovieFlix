package com.devsuperior.movieflix.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.movieflix.dtos.MovieDTO;

public interface MovieService {
	
	MovieDTO insert(MovieDTO dto);
	
	void delete(Long id);
	
	MovieDTO update(Long id, MovieDTO dto);
	
	Page<MovieDTO> findAllPaged(Pageable pageable);
	
	MovieDTO findById(Long id);
}
