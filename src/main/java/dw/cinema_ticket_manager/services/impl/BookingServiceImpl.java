package dw.cinema_ticket_manager.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import dw.cinema_ticket_manager.model.Booking;
import dw.cinema_ticket_manager.repositories.BookingRepository;
import dw.cinema_ticket_manager.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

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

    @Override
    public String generateQRCodeBase64(String text, int height, int width) throws WriterException, IOException {
        var hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        var qrWriter = new QRCodeWriter();
        var bitMatrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        var outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        var imgBytes = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imgBytes);
    }
}
