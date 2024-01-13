package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Booking;
import dw.cinema_ticket_manager.repositories.BookingRepository;
import dw.cinema_ticket_manager.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }
}
