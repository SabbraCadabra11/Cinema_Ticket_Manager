package dw.cinema_ticket_manager.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dw.cinema_ticket_manager.model.Movie;
import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.repositories.GenreRepository;
import dw.cinema_ticket_manager.repositories.MovieRepository;
import dw.cinema_ticket_manager.repositories.RoomRepository;
import dw.cinema_ticket_manager.repositories.SeatRepository;
import dw.cinema_ticket_manager.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitializationServiceImpl implements InitializationService {
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public InitializationServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository,
                                     MovieRepository movieRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void initialize() {
        setUpRooms();
        //setUpGenres();
    }

    @Override
    public void setUpRooms() {
        Room room1 = new Room(1, 10, 10);
        Room room2 = new Room(1, 10, 10);
        List<Seat> seats1 = createSeats(room1);
        List<Seat> seats2 = createSeats(room2);

        roomRepository.saveAll(List.of(room1, room2));
        seatRepository.saveAll(seats1);
        seatRepository.saveAll(seats2);
    }

/*    @Override
    public void setUpGenres() {
        List<String> genreNames = List.of("Akcja", "Animacja", "Anime", "Baśń", "Biograficzny", "Dokumentalny",
                "Dramat", "Familijny", "Fantasy", "Film-Noir", "Gangsterski", "Historyczny", "Horror",
                "Katastroficzny", "Komedia", "Komedia romantyczna", "Kostiumowy", "Krótkometrażowy", "Kryminalny",
                "Melodramat", "Musical", "Obyczajowy", "Przygodowy", "Przyrodniczy", "Psychologiczny", "Romans",
                "Science-fiction", "Sensacyjny", "Sportowy", "Surrealistyczny", "Świąteczny", "Szpiegowski",
                "Thriller", "True crime", "Western", "Wojenny");

        for (String genreName : genreNames) {
            genreRepository.save(new Genre(genreName));
        }
    }*/

    @Override
    public void setUpMovies() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Movie> movies = objectMapper.readValue(
                    new File("resources/static/movies.json"), new TypeReference<List<Movie>>() {});
            movieRepository.saveAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Seat> createSeats(Room room) {
        List<Seat> seats = new ArrayList<>();
        int rows = room.getRows();
        int columns = room.getColumns();

        for (int r = 0; r < rows; r++) {
            char rowLetter = (char)(r + 65);
            for (int c = 1; c <= columns; c++) {
                Seat seat = new Seat(room, String.valueOf(rowLetter + c));
                seats.add(seat);
            }
        }
        return seats;
    }
}









