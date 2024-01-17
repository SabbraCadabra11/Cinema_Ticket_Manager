package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.model.SeatStatus;
import dw.cinema_ticket_manager.services.impl.BookingServiceImpl;
import dw.cinema_ticket_manager.services.impl.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/refund")
public class RefundController {
    private final BookingServiceImpl bookingService;
    private final SeatServiceImpl seatService;

    @Autowired
    public RefundController(BookingServiceImpl bookingService, SeatServiceImpl seatService) {
        this.bookingService = bookingService;
        this.seatService = seatService;
    }

    @GetMapping
    public String renderRefundPage(Model model) {
        model.addAttribute("refundKey", "");
        return "refund";
    }

    @PostMapping
    public String handleRefund(@ModelAttribute("refundKey") String refundKey, Model model) {
        var uuidPattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if (!refundKey.matches(uuidPattern)) {
            model.addAttribute("message", "Upewnij się, że podałeś poprawny kod zwrotu");
            return "fragments/refund_feedback";
        }

        var bookingId = UUID.fromString(refundKey);
        var booking = bookingService.getBookingById(bookingId);
        if (booking.isPresent()) {
            var seats = booking.get().getBookedSeats();
            seats.forEach(seat -> seat.setStatus(SeatStatus.AVAILABLE));
            seatService.updateAll(seats);
            model.addAttribute("message", "Zwrot zakończony pomyślnie");
        } else {
            model.addAttribute("message", "Nie znaleziono biletu o podanym kodzie zwrotu");
        }
        return "fragments/refund_feedback";
    }

}










