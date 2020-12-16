package sg.edu.iss.team8ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import sg.edu.iss.team8ca.service.SendEmailService;


@SpringBootApplication
public class Team8caApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Team8caApplication.class, args);
	}

}
