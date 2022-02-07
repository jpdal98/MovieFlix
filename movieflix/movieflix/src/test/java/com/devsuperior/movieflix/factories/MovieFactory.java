package com.devsuperior.movieflix.factories;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class MovieFactory {

	public static Movie createMovie() {
		Movie movie = new Movie(1L, "Esqueceram de mim", 1990, "https://br.web.img3.acsta.net/medias/nmedia/18/93/93/95/20287484.jpg", "Uma família de Chicago planeja passar o Natal em Paris. Porém, em meio às confusões da viagem, um dos filhos, Kevin (Macaulay Culkin), acaba esquecido em casa. O garoto de apenas oito anos é obrigado a se virar sozinho e defender a casa de dois insistentes ladrões.");
		movie.setGenre(new Genre(1L, "Infatil"));
		return movie;
	}
	
	public static MovieDTO createMovieDTO() {
		MovieDTO movieDTO = new MovieDTO(createMovie());
		return movieDTO;
	}
}
