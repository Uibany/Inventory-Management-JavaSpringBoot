package sg.edu.iss.team8ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Login;
import sg.edu.iss.team8ca.repo.LoginRepo;

@Service
@Transactional
public class LoginImpl implements LoginInterface {
	
	@Autowired
	LoginRepo logrepo;

	@Override
	public void createLogin(Login login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLogin(Login login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Login> listAllLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLogin(Login login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean authenticateLogin(Login login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Login findbyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
