package dw.cinema_ticket_manager.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dw.cinema_ticket_manager.model.*;
import dw.cinema_ticket_manager.repositories.MovieRepository;
import dw.cinema_ticket_manager.repositories.RoomRepository;
import dw.cinema_ticket_manager.repositories.SeatRepository;
import dw.cinema_ticket_manager.repositories.ShowtimeRepository;
import dw.cinema_ticket_manager.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitializationServiceImpl implements InitializationService {
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public InitializationServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository,
                                     MovieRepository movieRepository, ShowtimeRepository showtimeRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
        this.showtimeRepository = showtimeRepository;
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void initialize() {
        setUpMovies();
        setUpShowtimes();
    }

    @Override
    public void setUpMovies() {
        try (InputStream inputStream = getClass().getResourceAsStream("/static/movies.json")) {
            List<Movie> movies = objectMapper.readValue(inputStream, new TypeReference<>() {});
            movieRepository.saveAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUpShowtimes() {
        try (InputStream inputStream = getClass().getResourceAsStream("/static/showtimes.json")) {
            List<Showtime> showtimes = objectMapper.readValue(inputStream, new TypeReference<>() {});
            var roomFactory = new RoomFactory();
            for (var showtime : showtimes) {
                var room = roomFactory.getRoomCopy(showtime.getRoomNumber());
                roomRepository.save(room);
                seatRepository.saveAll(room.getSeats());
                showtime.setRoom(room);
            }
            showtimeRepository.saveAll(showtimes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









