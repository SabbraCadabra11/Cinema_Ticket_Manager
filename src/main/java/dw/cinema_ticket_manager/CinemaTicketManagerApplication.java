package dw.cinema_ticket_manager;

import dw.cinema_ticket_manager.services.impl.InitializationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaTicketManagerApplication implements CommandLineRunner {

	private final InitializationServiceImpl initializationService;

	@Autowired
	public CinemaTicketManagerApplication(InitializationServiceImpl initializationService) {
		this.initializationService = initializationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializationService.initialize();
	}
}
