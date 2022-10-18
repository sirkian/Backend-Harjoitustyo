package backend.harjoitustyo.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignupForm {

	@NotEmpty(message = "SIGNUPFORM MSG")
	@Size (max = 30)
	private String username = "";
	
	@NotEmpty(message = "SIGNUPFORM MSG")
	@Size (max = 50)
	private String email = "";
	
	@NotEmpty
	private String role = "USER";
	
	@Size (min = 6, max = 50)
	private String password = "";
	
	@Size (min = 6, max = 50)
	private String passwordCheck = "";
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
}
