package dw.cinema_ticket_manager.services;

import dw.cinema_ticket_manager.model.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre);
    void addGenres(List<Genre> genres);
    Genre getGenreByName(String genreName);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();

}
