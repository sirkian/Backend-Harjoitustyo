package backend.harjoitustyo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "app_user")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long userId;
	
	@Size(min = 1, max = 30, message = "APPUSER MSG")
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Size(min = 1, max = 50, message = "APPUSER MSG")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	
	@JsonIgnore  
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser") 
	private List<Image> images; 
	 
	
	public AppUser() {
		
	}

	public AppUser(String username, String email, String role, String passwordHash) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.passwordHash = passwordHash;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public List<Image> getImages() { 
		return images; 
	}
	  
	public void setImages(List<Image> images) { 
		this.images = images; 
	}
	 

	@Override
	public String toString() {
		return "AppUser [userId=" + userId + ", username=" + username + ", email=" + email + ", role=" + role
				+ ", passwordHash=" + passwordHash + "]";
	}
}
