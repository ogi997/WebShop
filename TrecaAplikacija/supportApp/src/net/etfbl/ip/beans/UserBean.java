package net.etfbl.ip.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 8222837151611765655L;
	
	private Integer id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	
	private boolean logged = false;
	
	public UserBean() {}
	public UserBean (Integer id, String firstName, String lastName, String username, String password) {
		this.id = id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.username = username;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
}
