package com.devsuperior.movieflix.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public class MovieDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private Integer year;
	private String imgURI;
	private String synopsis;
	private Long genreId;
	private List<ReviewDTO> reviews = new ArrayList<>();
	
	public MovieDTO() {
		
	}

	public MovieDTO(Long id, String title, Integer year, String imgURI, String synopsis, Long genreId) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.imgURI = imgURI;
		this.synopsis = synopsis;
		this.genreId = genreId;
	}
	
	public MovieDTO(Movie entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.year = entity.getYear();
		this.imgURI = entity.getImgURI();
		this.synopsis = entity.getSynopsis();
		this.genreId = entity.getGenre().getId();
	}
	
	public MovieDTO(Movie entity, Set<Review> reviews) {
		this(entity);
		reviews.forEach(r -> this.reviews.add(new ReviewDTO(r)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgURI() {
		return imgURI;
	}

	public void setImgURI(String imgURI) {
		this.imgURI = imgURI;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public List<ReviewDTO> getReviews() {
		return reviews;
	}
}
