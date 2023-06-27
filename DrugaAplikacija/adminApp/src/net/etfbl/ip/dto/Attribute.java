package net.etfbl.ip.dto;

import java.io.Serializable;

public class Attribute implements Serializable {

	private static final long serialVersionUID = -8853360617552975150L;
	
	private Integer id;
	private String name;
	private Integer fk_category_id;
	private Byte deleted;
	
	public Attribute() {}
	
	public Attribute(Integer id, String name, Integer fk_category_id, Byte deleted) {
		this.id = id;
		this.name = name;
		this.fk_category_id = fk_category_id;
		this.deleted = deleted;
	}
	public Attribute(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Attribute(Integer id, Byte deleted) {
		this.id = id;
		this.deleted = deleted;
	}
	public Attribute(String name, Integer fkCategoryId) {
		this.name = name;
		this.fk_category_id = fkCategoryId;
	}
	
	public Attribute(String name, Integer fkCategoryId, Byte deleted) {
		this.name = name;
		this.fk_category_id = fkCategoryId;
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

	public Integer getFk_category_id() {
		return fk_category_id;
	}

	public void setFk_category_id(Integer fk_category_id) {
		this.fk_category_id = fk_category_id;
	}

	public Byte getDeleted() {
		return deleted;
	}

	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}

}
