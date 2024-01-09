package dw.cinema_ticket_manager.controllers;

import dw.cinema_ticket_manager.model.Room;
import dw.cinema_ticket_manager.model.Seat;
import dw.cinema_ticket_manager.model.Showtime;
import dw.cinema_ticket_manager.services.impl.BookingServiceImpl;
import dw.cinema_ticket_manager.services.impl.SeatServiceImpl;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final ShowtimeServiceImpl showtimeService;
    private final BookingServiceImpl bookingService;
    private final SeatServiceImpl seatService;
    private Showtime showtime;
    private Room room;
    private final List<Seat> chosenSeats;

    @Autowired
    public BookingController(ShowtimeServiceImpl showtimeService,
                             BookingServiceImpl bookingService,
                             SeatServiceImpl seatService) {
        this.showtimeService = showtimeService;
        this.bookingService = bookingService;
        this.seatService = seatService;
        showtime = null;
        room = null;
        chosenSeats = new ArrayList<>();
    }

    @GetMapping(params = "showtimeId")
    public String renderBookingPage(@RequestParam(name = "showtimeId") String showtimeId, Model model) {
        showtime = showtimeService.getShowtimeById(UUID.fromString(showtimeId));
        room = showtime.getRoom();
        model.addAttribute("movie", showtime.getMovie());
        model.addAttribute("roomNumber", showtime.getRoom().getRoomNumber());
        model.addAttribute("eventDate", getFormattedDate(showtime.getEventDate()));
        model.addAttribute("eventTime", showtime.getEventTime().toString());
        model.addAttribute("seats", seatService.getSeatsInRowsFromRoom(room));
        return "booking";
    }

    @PostMapping(params = {"row", "column", "isSeatAvailable"})
    public String changeSeatStatus(@RequestParam(name = "row") int seatRow,
                                   @RequestParam(name = "column") int seatColumn,
                                   @RequestParam(name = "isSeatAvailable") String status,
                                   Model model) {

        if (seatRow < 1 || seatRow > room.getRows() || seatColumn < 1 || seatColumn > room.getColumns()) {
            //TODO: handle this properly
            return "Invalid parameters";
        }

        var seat = seatService.getSeatByRoomRowAndColumn(room, seatRow, seatColumn);
        seat.setIsAvailable(Boolean.parseBoolean(status));
        if (seat.isAvailable()) {
            chosenSeats.remove(seat);
        } else {
            chosenSeats.add(seat);
        }
        System.out.println(chosenSeats.size());
        model.addAttribute("seat", seat);
        return seat.isAvailable() ? "booking :: seatButtonAvailable" : "booking :: seatButtonTaken";
    }

    @GetMapping("/updatePrice")
    public String updatePriceLabel(Model model) {
        int totalPrice = showtime.getBasePrice() * chosenSeats.size();
        model.addAttribute("totalPrice", totalPrice);
        return "booking :: updatedPriceLabel";
    }

    private String getFormattedDate(LocalDate date) {
        return switch (date.getMonthValue()) {
            case 1 -> "%d %s %d".formatted(date.getDayOfMonth(), "sty. ", date.getYear());
            case 2 -> "%d %s %d".formatted(date.getDayOfMonth(), "lut. ", date.getYear());
            case 3 -> "%d %s %d".formatted(date.getDayOfMonth(), "mar. ", date.getYear());
            case 4 -> "%d %s %d".formatted(date.getDayOfMonth(), "kwi. ", date.getYear());
            case 5 -> "%d %s %d".formatted(date.getDayOfMonth(), "maja ", date.getYear());
            case 6 -> "%d %s %d".formatted(date.getDayOfMonth(), "cze. ", date.getYear());
            case 7 -> "%d %s %d".formatted(date.getDayOfMonth(), "lip. ", date.getYear());
            case 8 -> "%d %s %d".formatted(date.getDayOfMonth(), "sie. ", date.getYear());
            case 9 -> "%d %s %d".formatted(date.getDayOfMonth(), "wrz. ", date.getYear());
            case 10 -> "%d %s %d".formatted(date.getDayOfMonth(), "paź. ", date.getYear());
            case 11 -> "%d %s %d".formatted(date.getDayOfMonth(), "lis. ", date.getYear());
            case 12 -> "%d %s %d".formatted(date.getDayOfMonth(), "gru. ", date.getYear());
            default -> "";
        };
    }

}
















