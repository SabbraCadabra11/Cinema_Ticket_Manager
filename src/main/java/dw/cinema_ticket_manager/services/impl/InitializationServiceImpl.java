package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.repositories.RoomRepository;
import dw.cinema_ticket_manager.repositories.SeatRepository;
import dw.cinema_ticket_manager.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitializationServiceImpl implements InitializationService {
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public InitializationServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public void initialize() {
        setUpRooms();
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









