package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Movie;
import dw.cinema_ticket_manager.repositories.MovieRepository;
import dw.cinema_ticket_manager.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getMovieById(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genreName) {
        return movieRepository.findByGenresName(genreName);
    }

    @Override
    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(long id) {

    }
}
