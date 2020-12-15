package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.*;

public interface CRUDInterface {
	
	public void createUser(User user);
	public void deleteUser(User user);
	public List<User> findAllUser();
	public User findUserById(Long id);
	
}

