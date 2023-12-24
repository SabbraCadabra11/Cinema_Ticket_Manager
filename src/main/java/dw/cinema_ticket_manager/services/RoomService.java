package dw.cinema_ticket_manager.services;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;

import java.util.List;

public interface RoomService {
    Room getRoomById(long id);
    Room getRoomByRoomNumber(int roomNumber);
    boolean addRoom(List<Seat> seats, int roomNumber, int rows, int columns);
    void deleteRoomById(long id);
    void deleteRoomByRoomNumber(int roomNumber);
}
