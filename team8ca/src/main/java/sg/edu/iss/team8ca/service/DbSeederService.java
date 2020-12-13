package sg.edu.iss.team8ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.repo.UserRepo;

@Component
public class DbSeederService implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {
		loadUserData();	
	}
	
	private void loadUserData() {
		if (userRepo.count() == 0) {
			User user1 = new User("admin", "password");
			userRepo.save(user1);
		}
		System.out.println(userRepo.count());
	}
	
}
