package com.devsuperior.movieflix.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.movieflix.dtos.GenreDTO;

public interface GenreService {
	
	GenreDTO insert(GenreDTO dto);
	
	void delete(Long id);
	
	GenreDTO update(Long id, GenreDTO dto);
	
	Page<GenreDTO> findAllPaged(Pageable pageable);
	
	GenreDTO findById(Long id);
}
