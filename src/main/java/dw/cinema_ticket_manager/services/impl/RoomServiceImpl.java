package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.repositories.RoomRepository;
import dw.cinema_ticket_manager.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.getRoomById(id);
    }

    @Override
    public Room getRoomByRoomNumber(int roomNumber) {
        return roomRepository.getRoomByRoomNumber(roomNumber);
    }

    @Override
    public boolean addRoom(List<Seat> seats, int roomNumber, int rows, int columns) {
        if (roomRepository.getRoomByRoomNumber(roomNumber) == null) {
            roomRepository.save(new Room(seats, roomNumber, rows, columns));
            return true;
        }
        return false;
    }

    @Override
    public void deleteRoomById(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void deleteRoomByRoomNumber(int roomNumber) {
        roomRepository.deleteByRoomNumber(roomNumber);
    }
}
