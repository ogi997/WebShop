package net.etfbl.ip.dao;

import java.util.List;

import net.etfbl.ip.dto.Category;
import net.etfbl.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM category WHERE deleted = 0";
	private static final String SQL_INSERT = "INSERT INTO category (name, deleted) VALUES (?, ?)";
	private static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
	private static final String SQL_UPDATE_CATEGORY_BY_ID = "UPDATE category SET name=? WHERE id = ?";
	private static final String SQL_DELETE_CATEGORY_BY_ID = "UPDATE category SET deleted=1 WHERE id = ?";
	
	public static int delete(Category category) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {category.getId()};
			ps = DBUtil.prepareStatement(c, SQL_DELETE_CATEGORY_BY_ID, true, values);
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
	
	public static int update(Category category) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {category.getName(), category.getId()};
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_CATEGORY_BY_ID, false, values);
			retValue = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
	
	public static Category getCategoryById(Integer id) {
		Category category = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SQL_SELECT_CATEGORY_BY_ID, false, values);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				category = new Category(rs.getInt(1), rs.getString(2), rs.getByte(3));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		// System.out.printf("WRAPPER: ", user);
		return category;
	}
	
	public static int insert(Category category) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// if (selectByUsername(user.getUsername()) == -1) return -1;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {category.getName(), 0};
			ps = DBUtil.prepareStatement(c, SQL_INSERT, true, values);
			retValue = ps.executeUpdate();
			
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Integer id = rs.getInt(1);
					category.setId(id);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return retValue;
	}
	
	public static List<Category> selectAll() {
		List<Category> categories = new ArrayList<>();
		
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);
			
			while(rs.next()) {
				categories.add(new Category(rs.getInt(1), rs.getString(2), rs.getByte(3)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}
		
		return categories;
	}

}
