package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.repositories.SeatRepository;
import dw.cinema_ticket_manager.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}
