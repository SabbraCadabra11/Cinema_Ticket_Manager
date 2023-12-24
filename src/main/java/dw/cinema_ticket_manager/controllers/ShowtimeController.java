package dw.cinema_ticket_manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowtimeController {

    @GetMapping("/showtimes")
    public String showtimes(Model model) {
        model.addAttribute("message", "Welcome from Thymeleaf");
        return "showtimes";
    }
}
