package dw.cinema_ticket_manager.services;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @PreDestroy
    public void onDestroy() {
        System.out.println("Closing application");
    }
}
