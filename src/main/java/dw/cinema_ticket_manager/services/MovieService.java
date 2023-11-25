package dw.cinema_ticket_manager.services;

import dw.cinema_ticket_manager.model.Movie;

import java.util.List;

public interface MovieService {
    Movie getMovieById(long id);
    List<Movie> getAllMovies();
    List<Movie> getMoviesByGenre(String genreName);
    List<Movie> getMoviesByReleaseYear(int releaseYear);
    void addMovie(Movie movie);
    void deleteMovie(long id);
}
