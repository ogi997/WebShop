package net.etfbl.ip.dto;

import java.io.Serializable;

public class Avatar implements Serializable {

	private static final long serialVersionUID = 5871792985498755662L;
	
	private Integer id;
	private Integer fk_user_avatar;
	private String name;
	
	public Avatar() {}
	public Avatar(Integer id, Integer fkUserAvatar, String name) {
		this.id = id;
		this.fk_user_avatar = fkUserAvatar;
		this.name = name;
	}
	
	public Avatar(Integer fkUserAvatar, String name) {
		this.fk_user_avatar = fkUserAvatar;
		this.name = name;
	}
	public Avatar(Integer fkUserAvatar) {
		this.fk_user_avatar = fkUserAvatar;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFk_user_avatar() {
		return fk_user_avatar;
	}
	public void setFk_user_avatar(Integer fk_user_avatar) {
		this.fk_user_avatar = fk_user_avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
