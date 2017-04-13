package Bean;

public class User {
	private String username;
	private String password;
	private String email;
	public User(String uname,String password,String email) {
		// TODO Auto-generated constructor stub
		setUsername(uname);
		setPassword(password);
		setEmail(email);
	}
	public User(String uname,String password) {
		// TODO Auto-generated constructor stub
		setUsername(uname);
		setPassword(password);
		setEmail(uname);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
