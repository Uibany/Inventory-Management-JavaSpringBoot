package sg.edu.iss.team8ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private UserType user;
	
	public Login() {
		super();
	}

	public Login(int id, String username, String password, UserType user) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
	}

	public Login(String username, String password, UserType user) {
		super();
		this.username = username;
		this.password = password;
		this.user = user;
	}

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUser() {
		return user;
	}

	public void setUser(UserType user) {
		this.user = user;
	}
	
}
