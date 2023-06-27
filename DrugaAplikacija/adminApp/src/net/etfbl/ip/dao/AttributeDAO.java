package net.etfbl.ip.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import net.etfbl.ip.dto.Attribute;
import net.etfbl.utils.DBUtil;

public class AttributeDAO {
	
	private static final String SQL_SELECT_ALL = "SELECT * FROM attribute WHERE deleted=0 AND fk_category_id = ?";
	private static final String SQL_INSERT = "INSERT INTO attribute (name, fk_category_id, deleted) VALUES (?, ?, ?)";
	private static final String SQL_SELECT_ATTRIBUTE_BY_ID = "SELECT * FROM attribute WHERE id = ?";
	private static final String SQL_UPDATE_ATTRIBUTE_BY_ID = "UPDATE attribute SET name=? WHERE id = ?";
	private static final String SQL_DELETE_ATTRIBUTE_BY_ID = "UPDATE attribute SET deleted=1 WHERE id = ?";

	public static int delete(Attribute attribute) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {attribute.getId()};
			ps = DBUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE_BY_ID, true, values);
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
	
	public static int update(Attribute attribute) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {attribute.getName(), attribute.getId()};
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_ATTRIBUTE_BY_ID, false, values);
			retValue = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		
		return retValue;
	}
	
	
	public static Attribute getAttributeById(Integer id) {
		Attribute attribute = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {id};
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ATTRIBUTE_BY_ID, false, values);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				attribute = new Attribute(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getByte(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		// System.out.printf("WRAPPER: ", user);
		return attribute;
	}
	
	public static int insert(Attribute attribute) {
		int retValue = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// if (selectByUsername(user.getUsername()) == -1) return -1;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {attribute.getName(), attribute.getFk_category_id(), 0};
			ps = DBUtil.prepareStatement(c, SQL_INSERT, true, values);
			retValue = ps.executeUpdate();
			
			if (retValue != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Integer id = rs.getInt(1);
					attribute.setId(id);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return retValue;
	}


	public static List<Attribute> selectAll(Integer categoryId) {
		List<Attribute> attributes = new ArrayList<>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = DBUtil.getConnection();
			Object[] values = {categoryId};
			ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				attributes.add(new Attribute(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getByte(4)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		return attributes;
	}

}
