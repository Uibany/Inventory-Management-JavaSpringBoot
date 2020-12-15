package sg.edu.iss.team8ca.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.Role;
import sg.edu.iss.team8ca.model.User;
import sg.edu.iss.team8ca.repo.RoleRepo;
import sg.edu.iss.team8ca.repo.UserRepo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements UserInterface{

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepo.save(user);
    }

	@Transactional
	public void deleteUser(User user) {
		userRepo.delete(user);

	}

	@Transactional
	public List<User> findAllUser() {
		return userRepo.findAll();
	}

	@Transactional
	public User findUserById(Long id) {
		return userRepo.findById(id).get();
	}

}

