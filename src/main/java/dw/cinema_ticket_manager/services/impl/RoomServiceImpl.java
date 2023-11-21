package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.repositories.RoomRepository;
import dw.cinema_ticket_manager.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
}
