package sg.edu.iss.team8ca.model;

public class Login {
	
	private int id;
	private String username;
	private String passowrd;
	
	public Login() {
		super();
	}

	public Login(int id, String username, String passowrd) {
		super();
		this.id = id;
		this.username = username;
		this.passowrd = passowrd;
	}

	public Login(String username, String passowrd) {
		super();
		this.username = username;
		this.passowrd = passowrd;
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

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

}
