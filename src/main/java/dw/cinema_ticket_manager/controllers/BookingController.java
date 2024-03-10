package dw.cinema_ticket_manager.controllers;

import com.google.zxing.WriterException;
import dw.cinema_ticket_manager.model.*;
import dw.cinema_ticket_manager.services.impl.BookingServiceImpl;
import dw.cinema_ticket_manager.services.impl.SeatServiceImpl;
import dw.cinema_ticket_manager.services.impl.ShowtimeServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final ShowtimeServiceImpl showtimeService;
    private final BookingServiceImpl bookingService;
    private final SeatServiceImpl seatService;

    @Autowired
    public BookingController(ShowtimeServiceImpl showtimeService,
                             BookingServiceImpl bookingService,
                             SeatServiceImpl seatService) {
        this.showtimeService = showtimeService;
        this.bookingService = bookingService;
        this.seatService = seatService;
    }

    @GetMapping(params = "showtimeId")
    public String renderBookingPage(@RequestParam(name = "showtimeId") String showtimeId, Model model, HttpSession session) {
        session.removeAttribute("chosenSeats");
        Showtime showtime = showtimeService.getShowtimeById(UUID.fromString(showtimeId));
        Room room = showtime.getRoom();
        model.addAttribute("movie", showtime.getMovie());
        model.addAttribute("roomNumber", showtime.getRoom().getRoomNumber());
        model.addAttribute("eventDate", showtime.getShortenedPlFormattedDate());
        model.addAttribute("eventTime", showtime.getEventTime().toString());
        model.addAttribute("seats", seatService.getSeatsInRowsFromRoom(room));
        session.setAttribute("showtime", showtime);
        session.setAttribute("room", room);
        return "booking";
    }

    @PostMapping(params = {"row", "column", "chosen"})
    public String handleSeatClick(@RequestParam(name = "row") int seatRow,
                                  @RequestParam(name = "column") int seatColumn,
                                  @RequestParam(name = "chosen") String chosen,
                                  Model model, HttpSession session) {

        Room room = (Room) session.getAttribute("room");
        if (seatRow < 1 || seatRow > room.getRows() || seatColumn < 1 || seatColumn > room.getColumns()) {
            // TODO: handle this properly
            return "Invalid parameters";
        }

        Seat seat = seatService.getSeatByRoomRowAndColumn(room, seatRow, seatColumn);
        List<Seat> chosenSeats = (List<Seat>) session.getAttribute("chosenSeats");
        if (chosenSeats == null) {
            chosenSeats = new ArrayList<>();
            session.setAttribute("chosenSeats", chosenSeats);
        }

        if (Boolean.parseBoolean(chosen)) {
            chosenSeats.add(seat);
            seat.setStatus(SeatStatus.RESERVED);
        } else {
            chosenSeats.remove(seat);
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        model.addAttribute("seat", seat);
        return seat.getStatus() == SeatStatus.AVAILABLE
                ? "fragments/seats_layout :: seatButtonAvailable"
                : "fragments/seats_layout :: seatButtonChosen";
    }

    @GetMapping("/updatePrice")
    public String updatePriceLabel(Model model, HttpSession session) {
        Showtime showtime = (Showtime) session.getAttribute("showtime");
        List<Seat> chosenSeats = (List<Seat>) session.getAttribute("chosenSeats");
        int totalPrice = showtime.getBasePrice() * (chosenSeats != null ? chosenSeats.size() : 0);
        model.addAttribute("totalPrice", totalPrice);
        return "booking :: updatedPriceLabel";
    }

    @GetMapping("/updateSeatsList")
    public String updateChosenSeatsList(Model model, HttpSession session) {
        List<Seat> chosenSeats = (List<Seat>) session.getAttribute("chosenSeats");
        Showtime showtime = (Showtime) session.getAttribute("showtime");
        model.addAttribute("chosenSeats", chosenSeats);
        model.addAttribute("seatPrice", showtime.getBasePrice());
        return "booking :: chosenSeatsDetails";
    }

    @GetMapping("/seats")
    public String renderSeats(Model model, HttpSession session) {
        Room room = (Room) session.getAttribute("room");
        var seats = seatService.getSeatsInRowsFromRoom(room);
        model.addAttribute("seats", seats);
        return "booking :: seatsLayout";
    }

    @GetMapping("/purchase")
    public String redirectToPurchased(Model model, HttpSession session) throws IOException, WriterException {
        List<Seat> chosenSeats = (List<Seat>) session.getAttribute("chosenSeats");
        if (chosenSeats == null || chosenSeats.isEmpty()) {
            // TODO: handle this properly
        }

        Showtime showtime = (Showtime) session.getAttribute("showtime");
        var booking = new Booking(showtime, chosenSeats);
        seatService.updateAll(chosenSeats, SeatStatus.OCCUPIED);
        bookingService.save(booking);
        var base64QRCode = bookingService.generateQRCodeBase64(booking.getId().toString(), 200, 200);
        model.addAttribute("booking", booking);
        model.addAttribute("base64QRCode", base64QRCode);
        return "checkout";
    }
}
















