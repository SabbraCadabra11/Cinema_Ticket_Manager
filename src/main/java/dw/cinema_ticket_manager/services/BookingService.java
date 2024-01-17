package dw.cinema_ticket_manager.services;

import com.google.zxing.WriterException;
import dw.cinema_ticket_manager.model.Booking;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface BookingService {
    void save(Booking booking);
    void refund(UUID bookingId);
    Optional<Booking> getBookingById(UUID bookingId);
    String generateQRCodeBase64(String text, int height, int width) throws WriterException, IOException;
}
