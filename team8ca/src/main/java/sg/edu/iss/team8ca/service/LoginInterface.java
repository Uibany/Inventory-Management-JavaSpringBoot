package sg.edu.iss.team8ca.service;

import java.util.List;

import sg.edu.iss.team8ca.model.Login;

public interface LoginInterface {
	
	public void createLogin(Login login);
	public void updateLogin(Login login);
	public List<Login> listAllLogin();
	public void deleteLogin(Login login);
	public boolean authenticateLogin(Login login);
	public Login findbyName(String name);
}
