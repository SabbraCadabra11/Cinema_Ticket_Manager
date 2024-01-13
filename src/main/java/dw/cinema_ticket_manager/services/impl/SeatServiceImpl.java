package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.model.SeatStatus;
import dw.cinema_ticket_manager.repositories.SeatRepository;
import dw.cinema_ticket_manager.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat getSeatByRoomRowAndColumn(Room room, int row, int column) {
        return seatRepository.findByRoomAndRowAndColumn(room, row, column);
    }

    @Override
    public List<List<Seat>> getSeatsInRowsFromRoom(Room room) {
        var allRows = new ArrayList<List<Seat>>();
        for (int r = 1; r <= room.getRows(); r++) {
            var rowOfSeats = new ArrayList<Seat>();
            for (int c = 1; c <= room.getColumns(); c++) {
                rowOfSeats.add(seatRepository.findByRoomAndRowAndColumn(room, r, c));
            }
            allRows.add(rowOfSeats);
        }
        return allRows;
    }

    @Override
    public void updateAll(List<Seat> seats) {
        seats.forEach(seat -> seat.setStatus(SeatStatus.OCCUPIED));
        seatRepository.saveAll(seats);
    }
}
