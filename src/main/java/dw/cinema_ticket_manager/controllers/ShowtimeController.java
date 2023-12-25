package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.model.Showtime;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ShowtimeController {

    private final ShowtimeServiceImpl showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeServiceImpl showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/showtimes")
    public String showtimes(Model model) {
        var allDates = new ArrayList<LocalDate>();
        for (Showtime showtime : showtimeService.getAllShowtimes()) {
            if (!allDates.contains(showtime.getEventDate())) {
                allDates.add(showtime.getEventDate());
            }
        }
        model.addAttribute("allDates", allDates);
        return "showtimes";
    }
}
