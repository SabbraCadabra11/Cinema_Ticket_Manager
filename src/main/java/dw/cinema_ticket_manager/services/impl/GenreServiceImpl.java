package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.repositories.GenreRepository;
import dw.cinema_ticket_manager.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
