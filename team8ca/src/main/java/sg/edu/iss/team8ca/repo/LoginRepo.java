package sg.edu.iss.team8ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team8ca.model.Login;

public interface LoginRepo extends JpaRepository<Login, Long> {
	
	public Login findLoginByUserName(String u);
	
}