package net.etfbl.ip.beans;

import java.io.Serializable;

public class UserWebBean implements Serializable {

	private static final long serialVersionUID = -2968590716142630065L;
	
	private Integer id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String city;
	private String email;
	
	public UserWebBean() {}
	public UserWebBean(Integer id, String firstName, String lastName, String username, String password, String city, String email) {
		this.id = 0;
		this.first_name = firstName;
		this.last_name = lastName;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
