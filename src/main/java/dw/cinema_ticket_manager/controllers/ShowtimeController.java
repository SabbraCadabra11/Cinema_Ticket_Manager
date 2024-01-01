package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.model.Movie;
import dw.cinema_ticket_manager.model.Showtime;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    private final ShowtimeServiceImpl showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeServiceImpl showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public RedirectView showtimes(Model model) {
        var allDates = getAllShowtimeDates();
        var firstShowtimeDate = allDates.get(0);
        model.addAttribute("allDates", allDates);
        model.addAttribute("showtimes", showtimeService.getShowtimesByDate(firstShowtimeDate));
        return new RedirectView("/showtimes/" + firstShowtimeDate);
    }

    @GetMapping("/{date}")
    public String showtimesByDate(@PathVariable("date") String date, Model model) {
        validatePathVariable(date); // Validate the path variable here
        var allDates = getAllShowtimeDates();
        var showtimes = showtimeService.getShowtimesByDate(LocalDate.parse(date));
        var moviesWithShowtimes = getMoviesWithShowtimes(showtimes);
        model.addAttribute("allDates", allDates);
        model.addAttribute("moviesWithShowtimes", moviesWithShowtimes);
        return "showtimes";
    }

    private Map<Movie, List<Showtime>> getMoviesWithShowtimes(List<Showtime> showtimes) {
        var moviesWithShowtimes = new LinkedHashMap<Movie, List<Showtime>>();
        for (var showtime : showtimes) {
            var movie = showtime.getMovie();
            moviesWithShowtimes
                    .computeIfAbsent(movie, k -> new ArrayList<>())
                    .add(showtime);
        }
        return moviesWithShowtimes.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getTitle()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private List<LocalDate> getAllShowtimeDates() {
        return showtimeService.getAllShowtimes()
                .stream()
                .map(Showtime::getEventDate)
                .distinct()
                .collect(Collectors.toList());
    }

    private void validatePathVariable(String date) {
        var pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); // regex to validate date string
        var matcher = pattern.matcher(date);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid date format. Please provide a date in the format YYYY-MM-DD.");
        }
    }
}
