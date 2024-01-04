package dw.cinema_ticket_manager.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dw.cinema_ticket_manager.model.Movie;
import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.model.Showtime;
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
        setUpRooms();
        setUpMovies();
        setUpShowtimes();
    }


    @Override
    public void setUpRooms() {
        var room1 = new Room(1, 10, 10);
        var room2 = new Room(2, 10, 10);
        var seats1 = createSeats(room1);
        var seats2 = createSeats(room2);

        roomRepository.saveAll(List.of(room1, room2));
        seatRepository.saveAll(seats1);
        seatRepository.saveAll(seats2);
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
            showtimeRepository.saveAll(showtimes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Seat> createSeats(Room room) {
        var seats = new ArrayList<Seat>();
        int rows = room.getRows();
        int columns = room.getColumns();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                var seat = new Seat(room, r, c);
                seats.add(seat);
            }
        }
        return seats;
    }
}









