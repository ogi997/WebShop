package net.etfbl.ip.beans;

import java.io.Serializable;

import net.etfbl.ip.dao.AdminDAO;
import net.etfbl.ip.dto.Admin;

public class AdminBean implements Serializable {
	
	private boolean isLogged = false;

	private static final long serialVersionUID = 4416882921120321330L;
	
	public AdminBean() {}
	
	
	public Admin getAdminByUsernameAndPassword(String username, String password) {
		Admin admin = AdminDAO.getAdminByUsernameAndPassword(username, password);
		if (admin != null) {
			this.isLogged = true;
			return admin;
		}
		this.isLogged = false;
		return admin;
	}


	public boolean isLogged() {
		return isLogged;
	}


	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}
