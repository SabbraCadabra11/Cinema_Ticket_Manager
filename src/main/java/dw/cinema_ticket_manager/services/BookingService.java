package dw.cinema_ticket_manager.services;

import com.google.zxing.WriterException;
import dw.cinema_ticket_manager.model.Booking;

import java.io.IOException;

public interface BookingService {
    void save(Booking booking);
    String generateQRCodeBase64(String text, int height, int width) throws WriterException, IOException;
}
