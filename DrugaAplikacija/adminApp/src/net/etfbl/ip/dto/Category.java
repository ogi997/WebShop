package net.etfbl.ip.dto;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Byte deleted;
	
	public Category() {}
	public Category(String name) {
		this.name = name;
		this.deleted = 0;
	}
	public Category(Integer id, Byte deleted) {
		this.id = id;
		this.deleted = deleted;
	}
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Category(String name, Byte deleted) {
		this.name = name;
		this.deleted = deleted;
	}
	public Category(Integer id, String name, Byte deleted) {
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Byte getDeleted() {
		return deleted;
	}
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

	

}
