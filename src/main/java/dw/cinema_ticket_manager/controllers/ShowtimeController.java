package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.model.Showtime;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowtimeController {

    private final ShowtimeServiceImpl showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeServiceImpl showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/showtimes")
    public String showtimes(Model model) {
        model.addAttribute("allDates", getAllShowtimeDates());
        return "showtimes";
    }

    @GetMapping("/showtimes/{date}")
    public String showtimesForDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       LocalDate date, Model model) {
        System.out.println(date);
        var showtimes = showtimeService.getShowtimesByDate(date);
        showtimes.forEach(s -> System.out.println(s.getMovie().getTitle()));
        model.addAttribute("showtimes", showtimes);
        return "fragments/movie_list";
    }

    private List<LocalDate> getAllShowtimeDates() {
        var allDates = new ArrayList<LocalDate>();
        for (Showtime showtime : showtimeService.getAllShowtimes()) {
            if (!allDates.contains(showtime.getEventDate())) {
                allDates.add(showtime.getEventDate());
            }
        }
        return allDates;
    }
}
