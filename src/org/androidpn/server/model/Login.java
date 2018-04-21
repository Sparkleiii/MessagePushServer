package org.androidpn.server.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name ="account", nullable = false, length = 64, unique = true)
	private String account;
	
	@Column(name = "password", nullable = false, length = 64)
	private String password;
	
	@Column(name = "username", length = 64,unique = true)
	private String username;
	
	@Column(name = "created_date", updatable = false)
    private Date createdDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
