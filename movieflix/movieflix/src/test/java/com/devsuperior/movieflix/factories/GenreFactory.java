package com.devsuperior.movieflix.factories;

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;

public class GenreFactory {

	public static Genre createGenre() {
		Genre genre = new Genre(1L, "Infatil");
		return genre;
	}
	
	public static GenreDTO createGenreDTO() {
		GenreDTO genreDTO = new GenreDTO(createGenre());
		return genreDTO;
	}
}
