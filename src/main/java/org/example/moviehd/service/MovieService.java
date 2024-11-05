package org.example.moviehd.service;

import org.example.moviehd.entity.Movie;
import org.example.moviehd.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + id));

        // Yangilanishi kerak bo'lgan maydonlarni so'zlaymiz
        if (movieDetails.getTitle() != null) {
            movie.setTitle(movieDetails.getTitle());
        }
        if (movieDetails.getGenre() != null) {
            movie.setGenre(movieDetails.getGenre());
        }
        if (movieDetails.getDescription() != null) {
            movie.setDescription(movieDetails.getDescription());
        }
        if (movieDetails.getRating() != 0) {
            movie.setRating(movieDetails.getRating());
        }
        if (movieDetails.getImageUrl() != null) {
            movie.setImageUrl(movieDetails.getImageUrl());
        }
        if (movieDetails.getVideoUrl() != null) {
            movie.setVideoUrl(movieDetails.getVideoUrl());
        }

        return movieRepository.save(movie);

    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }
}
