package dw.cinema_ticket_manager.services;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.model.SeatStatus;

import java.util.List;

public interface SeatService {
    Seat getSeatByRoomRowAndColumn(Room room, int row, int column);
    List<List<Seat>> getSeatsInRowsFromRoom(Room room);

    void updateAll(List<Seat> seats, SeatStatus status);
}
