package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.services.impl.BookingServiceImpl;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class BookingController {
    private final ShowtimeServiceImpl showtimeService;
    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(ShowtimeServiceImpl showtimeService, BookingServiceImpl bookingService) {
        this.showtimeService = showtimeService;
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public String renderBookingPage(@RequestParam(name = "showtimeId") String showtimeId, Model model) {
        var showtime = showtimeService.getShowtimeById(UUID.fromString(showtimeId));
        model.addAttribute("movieTitle", showtime.getMovie().getTitle());
        model.addAttribute("roomNumber", showtime.getRoom().getRoomNumber());
        model.addAttribute("eventDate", showtime.getEventDate().toString());
        model.addAttribute("eventTime", showtime.getEventTime().toString());
        return "booking";
    }

}
