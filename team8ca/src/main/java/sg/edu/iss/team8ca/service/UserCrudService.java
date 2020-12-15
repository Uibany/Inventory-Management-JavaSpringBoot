package sg.edu.iss.team8ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.repo.UserRepo;

@Service
public class UserCrudService implements CRUDInterface {
	@Autowired
	UserRepo urepo;
	
	@Transactional
	public void createUser(User user) {
		urepo.save(user);

	}

	@Transactional
	public void deleteUser(User user) {
		urepo.delete(user);

	}

	@Transactional
	public List<User> findAllUser() {
		return urepo.findAll();
	}

	@Transactional
	public User findUserById(Long id) {
		return urepo.findById(id).get();
	}


}
