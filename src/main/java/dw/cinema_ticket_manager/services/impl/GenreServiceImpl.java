package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Genre;
import dw.cinema_ticket_manager.repositories.GenreRepository;
import dw.cinema_ticket_manager.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void addGenres(List<Genre> genres) {
        genreRepository.saveAll(genres);
    }

    @Override
    public Genre getGenreByName(String genreName) {
        return genreRepository.findGenreByName(genreName);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
