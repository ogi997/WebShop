package net.etfbl.ip.service;

import java.io.Serializable;
import java.sql.*;
import net.etfbl.ip.beans.UserBean;
import net.etfbl.ip.beans.UserWebBean;
import net.etfbl.ip.utils.DBUtil;

public class UserManager implements Serializable {

	private static final long serialVersionUID = 7883576413447303264L;
	
	private final String SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM support_admin WHERE username = ? AND password = ?";
	private final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	public UserManager() {}
	
	public UserWebBean getUsernameById(Integer id) {
		UserWebBean retValue = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SELECT_USER_BY_ID, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				retValue = new UserWebBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
	public UserBean login(String username, String password) {
		UserBean retValue = null;
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {username, password};
			ps = DBUtil.prepareStatement(c, SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				retValue = new UserBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
}
