package com.devsuperior.movieflix.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.movieflix.dtos.ReviewDTO;

public interface ReviewService {

	ReviewDTO insert(ReviewDTO dto);
	
	void delete(Long id);
	
	ReviewDTO update(Long id, ReviewDTO dto);
	
	Page<ReviewDTO> findAllPaged(Pageable pageable);
	
	ReviewDTO findById(Long id);
}
