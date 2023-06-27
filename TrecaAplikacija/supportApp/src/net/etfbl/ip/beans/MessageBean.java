package net.etfbl.ip.beans;

import java.io.Serializable;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 6647725897168797810L;
	
	private Integer id;
	private Integer fk_u; //korisnik koji je poslao poruku
	private String text;
	private Byte status;
	
	public MessageBean() {}
	public MessageBean(Integer id, Integer fkU, String text, Byte status) {
		this.id = id;
		this.fk_u = fkU;
		this.text = text;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFk_u() {
		return fk_u;
	}
	public void setFk_u(Integer fk_u) {
		this.fk_u = fk_u;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}

}
