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

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.ReviewService;
import com.devsuperior.movieflix.services.exceptions.DataBaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = reviewRepository.save(entity);
		return new ReviewDTO(entity);
		
	}

	public void delete(Long id) {
		try {
			reviewRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}
	
	@Transactional
	public ReviewDTO update(Long id, ReviewDTO dto) {
		try {
			Review entity = reviewRepository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = reviewRepository.save(entity);
			return new ReviewDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional(readOnly = true)
	public Page<ReviewDTO> findAllPaged(Pageable pageable){
		Page<Review> list = reviewRepository.findAll(pageable);
		return list.map(x -> new ReviewDTO(x));
	}

	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		Optional<Review> obj = reviewRepository.findById(id);
		Review entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTO(entity);
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setText(dto.getText());
		entity.setUser(userRepository.getById(dto.getUserId()));
		entity.setMovie(movieRepository.getById(dto.getMovieId()));
	}
}
