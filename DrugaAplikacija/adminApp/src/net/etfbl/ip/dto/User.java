package net.etfbl.ip.dto;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 6573322195180433292L;
	
	private Integer id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String city;
	private String email;
	private Byte active;
	private Integer pin_code;
	private Byte deleted;
	
	public User() {}
	public User(Integer id, Byte deleted) {
		this.id = id;
		this.deleted = deleted;
	}
	public User(Integer id, String firstName, String lastName, String username, String password, String city, String email) {
		this.id = id;
		this.first_name = firstName;
		this.last_name = lastName;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
	}
	public User(String firstName, String lastName, String username, String password, String city, String email) {
		this.first_name = firstName;
		this.last_name = lastName;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
		this.active = 0;
		this.pin_code = 0000;
		this.deleted = 0;
	}
	public User(Integer id, String firstName, String lastName, String username, String password, String city, String email, Byte active, Integer pinCode, Byte deleted) {
		this.setId(id);
		this.setFirst_name(firstName);
		this.last_name = lastName;
		this.setUsername(username);
		this.setPassword(password);
		this.setCity(city);
		this.setEmail(email);
		this.setActive(active);
		this.setPin_code(pinCode);
		this.setDeleted(deleted);
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
	public Byte getActive() {
		return active;
	}
	public void setActive(Byte active) {
		this.active = active;
	}
	public Integer getPin_code() {
		return pin_code;
	}
	public void setPin_code(Integer pin_code) {
		this.pin_code = pin_code;
	}
	public Byte getDeleted() {
		return deleted;
	}
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	

}
