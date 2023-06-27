package net.etfbl.ip.dao;

import java.util.List;

import net.etfbl.ip.dto.Avatar;
import net.etfbl.ip.dto.User;
import net.etfbl.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM users WHERE deleted = 0";
	private static final String SQL_INSERT = "INSERT INTO users (first_name, last_name, username, password, city, email, active, pin_code, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET first_name=?, last_name=?, username=?, password=?, city=?, email=? WHERE id=?";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username=?";
	private static final String SQL_DELETE_USER_BY_ID = "UPDATE users SET deleted=1 WHERE id=?";
	public static int delete(User user) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {user.getId()};
			ps = DBUtil.prepareStatement(c, SQL_DELETE_USER_BY_ID, true, values);
			retValue = ps.executeUpdate();
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					retValue = 1;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
		
	}
	
	
	public static int selectByUsername(String username) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {username};
			ps = DBUtil.prepareStatement(c, SQL_SELECT_BY_USERNAME, false, values);
			rs = ps.executeQuery();
			if (rs.next()) retValue = -1;
 		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		return retValue;
	}
	
	public static int update(User user) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getPassword(), user.getCity(), user.getEmail(), user.getId()};
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_USER_BY_ID, false, values);
			retValue = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
	
	public static User getUserById(Integer id) {
		User user = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_ID, false, values);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getByte(8), rs.getInt(9), rs.getByte(10));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		System.out.printf("WRAPPER: ", user);
		return user;
	}
	
	public static int insert(User user) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if (selectByUsername(user.getUsername()) == -1) return -1;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getPassword(), user.getCity(), user.getEmail(), user.getActive(), user.getPin_code(), user.getDeleted() };
			ps = DBUtil.prepareStatement(c, SQL_INSERT, true, values);
			retValue = ps.executeUpdate();
			
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Integer id = rs.getInt(1);
					AvatarDAO.insert(new Avatar(id, "defaultAvatar.jpeg"));
					user.setId(id);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return retValue;
	}
	
	public static List<User> selectAll() {
		List<User> users = new ArrayList<>();
		
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getByte(8), rs.getInt(9), rs.getByte(10)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}
		
		return users;
	}

}
